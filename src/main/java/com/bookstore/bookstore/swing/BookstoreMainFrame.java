package com.bookstore.bookstore.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Main window for the Bookstore Desktop Application
 * This demonstrates basic Swing GUI components and layouts
 */
public class BookstoreMainFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextArea booksListArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Set Look and Feel to system default
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookstoreMainFrame frame = new BookstoreMainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public BookstoreMainFrame() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setTitle("📚 Bookstore Management System");
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        
        // Input fields
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        priceField = new JTextField(10);
        
        // Text area for displaying books
        booksListArea = new JTextArea(15, 50);
        booksListArea.setEditable(false);
        booksListArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        booksListArea.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    
    private void setupLayout() {
        contentPane.setLayout(new BorderLayout(10, 10));
        
        // Top panel - Title
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("📚 Bookstore Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 118, 210));
        titlePanel.add(titleLabel);
        contentPane.add(titlePanel, BorderLayout.NORTH);
        
        // Left panel - Input form
        JPanel inputPanel = createInputPanel();
        contentPane.add(inputPanel, BorderLayout.WEST);
        
        // Center panel - Books list
        JPanel listPanel = createListPanel();
        contentPane.add(listPanel, BorderLayout.CENTER);
        
        // Bottom panel - Status
        JPanel statusPanel = new JPanel();
        statusPanel.add(new JLabel("Ready"));
        contentPane.add(statusPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add New Book"));
        panel.setPreferredSize(new Dimension(300, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Title
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        panel.add(titleField, gbc);
        
        // Author
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        panel.add(authorField, gbc);
        
        // Price
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        panel.add(priceField, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton addButton = new JButton("📝 Add Book");
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        panel.add(addButton, gbc);
        
        gbc.gridy = 4;
        JButton clearButton = new JButton("🗑️ Clear Fields");
        clearButton.setBackground(new Color(255, 152, 0));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        panel.add(clearButton, gbc);
        
        // Add action listeners
        addButton.addActionListener(e -> addBook());
        clearButton.addActionListener(e -> clearFields());
        
        return panel;
    }
    
    private JPanel createListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Books Inventory"));
        
        JScrollPane scrollPane = new JScrollPane(booksListArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Add initial content
        booksListArea.setText("📚 BOOKS INVENTORY\n" +
                "=====================================\n" +
                "Title                    | Author           | Price\n" +
                "-------------------------------------\n");
        
        return panel;
    }
    
    private void setupEventHandlers() {
        // Enter key in price field triggers add book
        priceField.addActionListener(e -> addBook());
    }
    
    private void addBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String priceText = priceField.getText().trim();
        
        if (title.isEmpty() || author.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "⚠️ Please fill in all fields!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            double price = Double.parseDouble(priceText);
            
            // Format and add to list
            String bookEntry = String.format("%-25s| %-17s| $%.2f\n", 
                title.length() > 25 ? title.substring(0, 22) + "..." : title,
                author.length() > 17 ? author.substring(0, 14) + "..." : author,
                price);
                
            booksListArea.append(bookEntry);
            
            // Clear fields
            clearFields();
            
            // Show success message
            JOptionPane.showMessageDialog(this, 
                "✅ Book added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "⚠️ Please enter a valid price!", 
                "Invalid Price", 
                JOptionPane.ERROR_MESSAGE);
            priceField.selectAll();
            priceField.requestFocus();
        }
    }
    
    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        priceField.setText("");
        titleField.requestFocus();
    }
}
