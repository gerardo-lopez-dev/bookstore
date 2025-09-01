package com.bookstore.bookstore;

import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {BookstoreApplication.class})
@ActiveProfiles("test")
class BookstoreApplicationTests {

	@Autowired
	private BookService bookService;

	@BeforeAll
	static void setUpHeadless() {
		// Ensure headless mode for tests
		System.setProperty("java.awt.headless", "true");
	}

	@Test
	void contextLoads() {
		// Test that the Spring context loads successfully
		assertNotNull(bookService);
	}

	@Test
	void testBookService() {
		// Test basic CRUD operations
		Book book = new Book();
		book.setTitle("Test Book");
		book.setAuthor("Test Author");
		book.setIsbn("123456789");
		book.setPrice(19.99);
		book.setStock(10);
		book.setDescription("Test Description");
		book.setAvailable(true);

		// Create
		Book savedBook = bookService.createBook(book);
		assertNotNull(savedBook.getId());
		assertEquals("Test Book", savedBook.getTitle());

		// Read
		Book foundBook = bookService.getBookById(savedBook.getId());
		assertNotNull(foundBook);
		assertEquals("Test Book", foundBook.getTitle());

		// Update
		foundBook.setTitle("Updated Test Book");
		Book updatedBook = bookService.updateBook(foundBook.getId(), foundBook);
		assertEquals("Updated Test Book", updatedBook.getTitle());

		// Delete
		bookService.deleteBook(savedBook.getId());
		Book deletedBook = bookService.getBookById(savedBook.getId());
		assertNull(deletedBook);
	}

}
