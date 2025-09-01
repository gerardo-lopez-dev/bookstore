package com.bookstore.bookstore.swing.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.awt.*;

/**
 * Condition to check if GUI environment is available
 */
public class GuiAvailableCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            // Check if headless mode is set
            if (GraphicsEnvironment.isHeadless()) {
                return false;
            }
            
            // Try to get the default display
            GraphicsEnvironment.getLocalGraphicsEnvironment();
            return true;
        } catch (Exception e) {
            // If any exception occurs, GUI is not available
            return false;
        }
    }
}
