package com.bookstore.bookstore.swing;

import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.service.BookService;
import com.bookstore.bookstore.swing.config.GuiAvailableCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Collections;

/**
 * Advanced Swing application with table and more sophisticated UI
 * Demonstrates MVC pattern in Swing applications with Spring integration
 */
@Component
@Conditional(GuiAvailableCondition.class)
public class AdvancedBookManagerFrame extends JFrame {
    
    // Service
    private final BookService bookService;
    
    // Model
    private List<Book> books;
    private DefaultTableModel tableModel;
    
    // View components
    private JTable booksTable;
    private JTextField titleField, authorField, isbnField, priceField, stockField;
    private JTextArea descriptionArea;
    private JCheckBox availableCheckBox;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JLabel statusLabel;
    
    
    // Constructor with dependency injection
    public AdvancedBookManagerFrame(BookService bookService) {
        this.bookService = bookService;
        initializeData();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        loadBooksFromService();
    }
    
    private void initializeData() {
        books = Collections.emptyList();
        
        // Table model setup
        String[] columnNames = {"ID", "Title", "Author", "ISBN", "Price", "Stock", "Available"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
    }
    
    private void initializeComponents() {
        setTitle("📚 Advanced Book Manager - Swing Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Input fields
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        isbnField = new JTextField(15);
        priceField = new JTextField(10);
        stockField = new JTextField(10);
        descriptionArea = new JTextArea(4, 20);
        availableCheckBox = new JCheckBox("Available", true);
        
        // Table
        booksTable = new JTable(tableModel);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksTable.setRowHeight(25);
        booksTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Buttons
        addButton = new JButton("➕ Add Book");
        updateButton = new JButton("✏️ Update Book");
        deleteButton = new JButton("🗑️ Delete Book");
        clearButton = new JButton("🔄 Clear Form");
        
        // Style buttons
        styleButton(addButton, new Color(76, 175, 80));
        styleButton(updateButton, new Color(33, 150, 243));
        styleButton(deleteButton, new Color(244, 67, 54));
        styleButton(clearButton, new Color(156, 39, 176));
        
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 118, 210));
        JLabel headerLabel = new JLabel("📚 Advanced Book Management System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(createFormPanel());
        splitPane.setRightComponent(createTablePanel());
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.4);
        
        add(splitPane, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = new JPanel();
        statusLabel = new JLabel("Status: Ready | Total Books: 0");
        footerPanel.add(statusLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Converts a Book entity to a table row array
     */
    private Object[] bookToTableRow(Book book) {
        return new Object[]{
            book.getId(),
            book.getTitle(), 
            book.getAuthor(), 
            book.getIsbn(), 
            book.getPrice(), 
            book.getStock(), 
            book.getAvailable() ? "✅" : "❌"
        };
    }
    
    /**
     * Updates the status label with current book count
     */
    private void updateStatusLabel() {
        statusLabel.setText("Status: Ready | Total Books: " + books.size());
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "📝 Book Details"));
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Form fields
        addFormField(panel, "Title:", titleField, gbc, 0);
        addFormField(panel, "Author:", authorField, gbc, 1);
        addFormField(panel, "ISBN:", isbnField, gbc, 2);
        addFormField(panel, "Price ($):", priceField, gbc, 3);
        addFormField(panel, "Stock:", stockField, gbc, 4);
        
        // Description
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setPreferredSize(new Dimension(200, 80));
        panel.add(descScroll, gbc);
        
        // Available checkbox
        gbc.gridx = 1; gbc.gridy = 6; gbc.gridwidth = 1;
        panel.add(availableCheckBox, gbc);
        
        // Buttons panel
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(createButtonPanel(), gbc);
        
        return panel;
    }
    
    private void addFormField(JPanel panel, String label, JTextField field, 
                             GridBagConstraints gbc, int row) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(clearButton);
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "📊 Books Inventory"));
        
        JScrollPane scrollPane = new JScrollPane(booksTable);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void setupEventHandlers() {
        addButton.addActionListener(e -> addBook());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
        clearButton.addActionListener(e -> clearForm());
        
        // Table selection handler
        booksTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = booksTable.getSelectedRow();
                if (selectedRow >= 0) {
                    loadBookToForm(selectedRow);
                    updateButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                } else {
                    updateButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            }
        });
    }
    
    private void addBook() {
        if (validateForm()) {
            try {
                Book book = createBookFromForm();
                Book savedBook = bookService.createBook(book);
                loadBooksFromService();
                clearForm();
                showMessage("✅ Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                showMessage("❌ Error adding book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void updateBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow >= 0 && validateForm()) {
            try {
                Book selectedBook = books.get(selectedRow);
                Book updatedBook = createBookFromForm();
                updatedBook.setId(selectedBook.getId());
                
                Book savedBook = bookService.updateBook(selectedBook.getId(), updatedBook);
                if (savedBook != null) {
                    loadBooksFromService();
                    clearForm();
                    showMessage("✅ Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    showMessage("❌ Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                showMessage("❌ Error updating book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow >= 0) {
            int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this book?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
                
            if (result == JOptionPane.YES_OPTION) {
                try {
                    Book selectedBook = books.get(selectedRow);
                    bookService.deleteBook(selectedBook.getId());
                    loadBooksFromService();
                    clearForm();
                    showMessage("🗑️ Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    showMessage("❌ Error deleting book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private boolean validateForm() {
        if (titleField.getText().trim().isEmpty()) {
            showMessage("Please enter a title!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            titleField.requestFocus();
            return false;
        }
        
        if (authorField.getText().trim().isEmpty()) {
            showMessage("Please enter an author!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            authorField.requestFocus();
            return false;
        }
        
        try {
            Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid price!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            priceField.requestFocus();
            return false;
        }
        
        try {
            Integer.parseInt(stockField.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid stock quantity!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            stockField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private Book createBookFromForm() {
        Book book = new Book();
        book.setTitle(titleField.getText().trim());
        book.setAuthor(authorField.getText().trim());
        book.setIsbn(isbnField.getText().trim());
        book.setPrice(Double.parseDouble(priceField.getText().trim()));
        book.setStock(Integer.parseInt(stockField.getText().trim()));
        book.setDescription(descriptionArea.getText().trim());
        book.setAvailable(availableCheckBox.isSelected());
        return book;
    }
    
    private void loadBookToForm(int row) {
        Book book = books.get(row);
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        isbnField.setText(book.getIsbn());
        priceField.setText(String.valueOf(book.getPrice()));
        stockField.setText(String.valueOf(book.getStock()));
        descriptionArea.setText(book.getDescription());
        availableCheckBox.setSelected(book.getAvailable());
    }
    
    private void clearForm() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        priceField.setText("");
        stockField.setText("");
        descriptionArea.setText("");
        availableCheckBox.setSelected(true);
        booksTable.clearSelection();
    }
    
    private void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }
    
    private void loadBooksFromService() {
        try {
            books = bookService.getAllBooks();
            
            // Clear existing table data
            tableModel.setRowCount(0);
            
            // Populate table with books from service
            for (Book book : books) {
                tableModel.addRow(bookToTableRow(book));
            }
            
            updateStatusLabel();
        } catch (Exception e) {
            showMessage("❌ Error loading books: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            books = Collections.emptyList();
            updateStatusLabel();
        }
    }
}
