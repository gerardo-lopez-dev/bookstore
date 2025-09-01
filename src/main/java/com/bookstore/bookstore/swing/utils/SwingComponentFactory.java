package com.bookstore.bookstore.swing.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for creating common Swing components with consistent styling
 * This helps to standardize the look and feel across the application
 */
public class SwingComponentFactory {
    
    // Color scheme
    public static final Color PRIMARY_COLOR = new Color(25, 118, 210);
    public static final Color SECONDARY_COLOR = new Color(76, 175, 80);
    public static final Color DANGER_COLOR = new Color(244, 67, 54);
    public static final Color WARNING_COLOR = new Color(255, 152, 0);
    public static final Color INFO_COLOR = new Color(33, 150, 243);
    
    // Fonts
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 12);
    public static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 12);
    
    /**
     * Creates a styled button with consistent appearance
     */
    public static JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 35));
        return button;
    }
    
    /**
     * Creates a primary action button
     */
    public static JButton createPrimaryButton(String text) {
        return createStyledButton(text, PRIMARY_COLOR);
    }
    
    /**
     * Creates a success button
     */
    public static JButton createSuccessButton(String text) {
        return createStyledButton(text, SECONDARY_COLOR);
    }
    
    /**
     * Creates a danger button
     */
    public static JButton createDangerButton(String text) {
        return createStyledButton(text, DANGER_COLOR);
    }
    
    /**
     * Creates a warning button
     */
    public static JButton createWarningButton(String text) {
        return createStyledButton(text, WARNING_COLOR);
    }
    
    /**
     * Creates a styled text field with consistent appearance
     */
    public static JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(LABEL_FONT);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return textField;
    }
    
    /**
     * Creates a styled text area with scroll pane
     */
    public static JScrollPane createStyledTextArea(int rows, int columns) {
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setFont(LABEL_FONT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        return scrollPane;
    }
    
    /**
     * Creates a styled label
     */
    public static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        return label;
    }
    
    /**
     * Creates a title label
     */
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE_FONT);
        label.setForeground(PRIMARY_COLOR);
        return label;
    }
    
    /**
     * Creates a styled panel with consistent borders
     */
    public static JPanel createStyledPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), title));
        return panel;
    }
    
    /**
     * Creates a form panel with GridBagLayout
     */
    public static JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return panel;
    }
    
    /**
     * Helper method to add components to GridBagLayout easily
     */
    public static void addToGridBag(JPanel panel, Component component, 
                                   int gridx, int gridy, int gridwidth, 
                                   int anchor, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.anchor = anchor;
        gbc.insets = insets;
        panel.add(component, gbc);
    }
    
    /**
     * Shows a styled message dialog
     */
    public static void showMessage(Component parent, String message, 
                                  String title, int messageType) {
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }
    
    /**
     * Shows a confirmation dialog
     */
    public static boolean showConfirmation(Component parent, String message, 
                                         String title) {
        int result = JOptionPane.showConfirmDialog(parent, message, title, 
                                                 JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
    
    /**
     * Sets the application-wide Look and Feel
     */
    public static void setLookAndFeel() {
        try {
            // Try to use Nimbus Look and Feel
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    
                    // Customize Nimbus colors
                    UIManager.put("nimbusBase", new Color(18, 30, 49));
                    UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
                    UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
                    UIManager.put("nimbusFocus", PRIMARY_COLOR);
                    UIManager.put("nimbusGreen", SECONDARY_COLOR);
                    UIManager.put("nimbusInfoBlue", INFO_COLOR);
                    UIManager.put("nimbusLightBackground", Color.WHITE);
                    UIManager.put("nimbusOrange", WARNING_COLOR);
                    UIManager.put("nimbusRed", DANGER_COLOR);
                    UIManager.put("nimbusSelectedText", Color.WHITE);
                    UIManager.put("nimbusSelectionBackground", PRIMARY_COLOR);
                    
                    return;
                }
            }
            
            // Fallback to system Look and Feel
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
