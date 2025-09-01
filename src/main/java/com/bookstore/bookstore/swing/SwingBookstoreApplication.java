package com.bookstore.bookstore.swing;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;

/**
 * Main class for the Swing-based bookstore application.
 * This class initializes the Spring context and launches the Swing GUI.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.bookstore.bookstore")
public class SwingBookstoreApplication {

    public static void main(String[] args) {
        // Disable headless mode for Swing applications
        System.setProperty("java.awt.headless", "false");
        
        // Set up Swing Look and Feel
        SwingUtilities.invokeLater(() -> {
            try {
                // Use Nimbus Look and Feel for modern appearance
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                // Fallback to default if Nimbus is not available
                e.printStackTrace();
            }
        });

        // Create Spring context with headless=false
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SwingBookstoreApplication.class)
                .headless(false)
                .run(args);

        // Get the main frame from Spring context and show it
        SwingUtilities.invokeLater(() -> {
            try {
                AdvancedBookManagerFrame mainFrame = context.getBean(AdvancedBookManagerFrame.class);
                mainFrame.setVisible(true);
            } catch (Exception e) {
                System.err.println("GUI not available or error creating main frame: " + e.getMessage());
                System.exit(1);
            }
        });
    }
}
