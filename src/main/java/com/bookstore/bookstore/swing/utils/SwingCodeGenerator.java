package com.bookstore.bookstore.swing.utils;

import java.util.Scanner;

/**
 * Code generator for Swing components
 * This utility helps generate boilerplate code for common Swing patterns
 */
public class SwingCodeGenerator {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🎨 Swing Code Generator");
        System.out.println("========================");
        System.out.println("1. Generate JFrame class");
        System.out.println("2. Generate JPanel class");
        System.out.println("3. Generate JDialog class");
        System.out.println("4. Generate CRUD Panel");
        System.out.println("5. Generate Form Panel");
        System.out.print("Choose option (1-5): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                generateJFrameClass();
                break;
            case 2:
                generateJPanelClass();
                break;
            case 3:
                generateJDialogClass();
                break;
            case 4:
                generateCRUDPanel();
                break;
            case 5:
                generateFormPanel();
                break;
            default:
                System.out.println("Invalid option");
        }
    }
    
    private static void generateJFrameClass() {
        System.out.print("Enter class name: ");
        String className = scanner.nextLine();
        System.out.print("Enter window title: ");
        String title = scanner.nextLine();
        
        String code = generateJFrameCode(className, title);
        System.out.println("\n📋 Generated Code:\n");
        System.out.println(code);
    }
    
    private static String generateJFrameCode(String className, String title) {
        return String.format("""
package com.bookstore.bookstore.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.bookstore.bookstore.swing.utils.SwingComponentFactory;

/**
 * %s - Generated Swing Frame
 */
public class %s extends JFrame {
    
    private JPanel contentPane;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingComponentFactory.setLookAndFeel();
            new %s().setVisible(true);
        });
    }
    
    public %s() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setTitle("%s");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        setContentPane(contentPane);
    }
    
    private void setupLayout() {
        contentPane.setLayout(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.add(SwingComponentFactory.createTitleLabel("%s"));
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        // Center content
        JPanel centerPanel = createCenterPanel();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.add(new JLabel("Ready"));
        contentPane.add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createCenterPanel() {
        JPanel panel = SwingComponentFactory.createStyledPanel("Main Content");
        
        // Add your components here
        panel.add(new JLabel("Content goes here"));
        
        return panel;
    }
    
    private void setupEventHandlers() {
        // Add event handlers here
    }
}
""", className, className, className, className, title, title);
    }
    
    private static void generateJPanelClass() {
        System.out.print("Enter panel class name: ");
        String className = scanner.nextLine();
        System.out.print("Enter panel title: ");
        String title = scanner.nextLine();
        
        String code = generateJPanelCode(className, title);
        System.out.println("\n📋 Generated Code:\n");
        System.out.println(code);
    }
    
    private static String generateJPanelCode(String className, String title) {
        return String.format("""
package com.bookstore.bookstore.swing.panels;

import javax.swing.*;
import java.awt.*;
import com.bookstore.bookstore.swing.utils.SwingComponentFactory;

/**
 * %s - Generated Swing Panel
 */
public class %s extends JPanel {
    
    public %s() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setBorder(BorderFactory.createTitledBorder("%s"));
    }
    
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Add components here
        gbc.gridx = 0; gbc.gridy = 0;
        add(SwingComponentFactory.createStyledLabel("Label:"), gbc);
        
        gbc.gridx = 1;
        add(SwingComponentFactory.createStyledTextField(20), gbc);
    }
    
    private void setupEventHandlers() {
        // Add event handlers here
    }
}
""", className, className, className, title);
    }
    
    private static void generateJDialogClass() {
        System.out.print("Enter dialog class name: ");
        String className = scanner.nextLine();
        System.out.print("Enter dialog title: ");
        String title = scanner.nextLine();
        
        String code = generateJDialogCode(className, title);
        System.out.println("\n📋 Generated Code:\n");
        System.out.println(code);
    }
    
    private static String generateJDialogCode(String className, String title) {
        return String.format("""
package com.bookstore.bookstore.swing.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.bookstore.bookstore.swing.utils.SwingComponentFactory;

/**
 * %s - Generated Swing Dialog
 */
public class %s extends JDialog {
    
    private boolean confirmed = false;
    
    public %s(Frame parent) {
        super(parent, "%s", true);
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setSize(400, 300);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Content panel
        JPanel contentPanel = SwingComponentFactory.createFormPanel();
        add(contentPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton okButton = SwingComponentFactory.createPrimaryButton("OK");
        JButton cancelButton = SwingComponentFactory.createDangerButton("Cancel");
        
        panel.add(okButton);
        panel.add(cancelButton);
        
        return panel;
    }
    
    private void setupEventHandlers() {
        // Add event handlers here
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
}
""", className, className, className, title);
    }
    
    private static void generateCRUDPanel() {
        System.out.print("Enter entity name (e.g., Book, User): ");
        String entityName = scanner.nextLine();
        
        String code = generateCRUDPanelCode(entityName);
        System.out.println("\n📋 Generated Code:\n");
        System.out.println(code);
    }
    
    private static String generateCRUDPanelCode(String entityName) {
        String className = entityName + "CRUDPanel";
        return String.format("""
package com.bookstore.bookstore.swing.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.bookstore.bookstore.swing.utils.SwingComponentFactory;

/**
 * CRUD Panel for %s management
 */
public class %s extends JPanel {
    
    private List<%s> items;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField; // Customize fields as needed
    private JButton addButton, updateButton, deleteButton, clearButton;
    
    public %s() {
        initializeData();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeData() {
        items = new ArrayList<>();
        
        String[] columnNames = {"ID", "Name"}; // Customize columns
        tableModel = new DefaultTableModel(columnNames, 0);
    }
    
    private void initializeComponents() {
        setBorder(BorderFactory.createTitledBorder("%s Management"));
        
        // Form fields
        nameField = SwingComponentFactory.createStyledTextField(20);
        
        // Table
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Buttons
        addButton = SwingComponentFactory.createSuccessButton("Add");
        updateButton = SwingComponentFactory.createPrimaryButton("Update");
        deleteButton = SwingComponentFactory.createDangerButton("Delete");
        clearButton = SwingComponentFactory.createWarningButton("Clear");
        
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Form panel
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.WEST);
        
        // Table panel
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
    }
    
    private JPanel createFormPanel() {
        JPanel panel = SwingComponentFactory.createStyledPanel("Add/Edit %s");
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(300, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Name field
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(SwingComponentFactory.createStyledLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = SwingComponentFactory.createStyledPanel("%s List");
        panel.setLayout(new BorderLayout());
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void setupEventHandlers() {
        addButton.addActionListener(e -> add%s());
        updateButton.addActionListener(e -> update%s());
        deleteButton.addActionListener(e -> delete%s());
        clearButton.addActionListener(e -> clearForm());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    load%sToForm(selectedRow);
                    updateButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                } else {
                    updateButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            }
        });
    }
    
    private void add%s() {
        if (validateForm()) {
            // Create new %s object and add to list
            // Update table model
            tableModel.addRow(new Object[]{items.size() + 1, nameField.getText()});
            clearForm();
            SwingComponentFactory.showMessage(this, "%s added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void update%s() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && validateForm()) {
            // Update existing %s object
            tableModel.setValueAt(nameField.getText(), selectedRow, 1);
            clearForm();
            SwingComponentFactory.showMessage(this, "%s updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void delete%s() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            if (SwingComponentFactory.showConfirmation(this, "Are you sure you want to delete this %s?", "Confirm Delete")) {
                items.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                clearForm();
                SwingComponentFactory.showMessage(this, "%s deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void load%sToForm(int row) {
        nameField.setText((String) tableModel.getValueAt(row, 1));
    }
    
    private void clearForm() {
        nameField.setText("");
        table.clearSelection();
    }
    
    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            SwingComponentFactory.showMessage(this, "Please enter a name!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocus();
            return false;
        }
        return true;
    }
}
""", entityName, className, entityName, className, entityName, entityName, 
            entityName, entityName, entityName, entityName, entityName, entityName, 
            entityName, entityName, entityName, entityName, entityName, entityName, 
            entityName, entityName, entityName, entityName);
    }
    
    private static void generateFormPanel() {
        System.out.print("Enter form name: ");
        String formName = scanner.nextLine();
        System.out.print("Enter number of fields: ");
        int fieldCount = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        String code = generateFormPanelCode(formName, fieldCount);
        System.out.println("\n📋 Generated Code:\n");
        System.out.println(code);
    }
    
    private static String generateFormPanelCode(String formName, int fieldCount) {
        String className = formName + "FormPanel";
        StringBuilder fieldsDeclaration = new StringBuilder();
        StringBuilder fieldsInitialization = new StringBuilder();
        StringBuilder fieldsLayout = new StringBuilder();
        
        for (int i = 1; i <= fieldCount; i++) {
            fieldsDeclaration.append(String.format("    private JTextField field%d;\n", i));
            fieldsInitialization.append(String.format("        field%d = SwingComponentFactory.createStyledTextField(20);\n", i));
            fieldsLayout.append(String.format("""
        gbc.gridx = 0; gbc.gridy = %d;
        add(SwingComponentFactory.createStyledLabel("Field %d:"), gbc);
        gbc.gridx = 1;
        add(field%d, gbc);
        
""", i - 1, i, i));
        }
        
        return String.format("""
package com.bookstore.bookstore.swing.panels;

import javax.swing.*;
import java.awt.*;
import com.bookstore.bookstore.swing.utils.SwingComponentFactory;

/**
 * %s - Generated Form Panel
 */
public class %s extends JPanel {
    
%s    
    public %s() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setBorder(BorderFactory.createTitledBorder("%s"));
        
%s    }
    
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
%s        
        // Submit button
        gbc.gridx = 0; gbc.gridy = %d; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton submitButton = SwingComponentFactory.createPrimaryButton("Submit");
        add(submitButton, gbc);
    }
    
    private void setupEventHandlers() {
        // Add event handlers here
    }
}
""", className, className, fieldsDeclaration.toString(), className, formName, 
            fieldsInitialization.toString(), fieldsLayout.toString(), fieldCount);
    }
}
