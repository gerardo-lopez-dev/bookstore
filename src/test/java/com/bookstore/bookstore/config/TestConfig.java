package com.bookstore.bookstore.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Test configuration that excludes Swing components
 */
@TestConfiguration
@ComponentScan(basePackages = "com.bookstore.bookstore",
               excludeFilters = @ComponentScan.Filter(
                   pattern = "com.bookstore.bookstore.swing.*"
               ))
public class TestConfig {
}
