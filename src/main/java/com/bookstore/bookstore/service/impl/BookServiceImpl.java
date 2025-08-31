package com.bookstore.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.service.BookService;

/**
 * Service implementation for book management.
 * Provides CRUD operations for the Book entity.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    /**
     * Constructor for dependency injection.
     * @param bookRepository book repository
     */
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Gets all books available in the database.
     * @return list of all books
     */
    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Finds a book by its ID.
     * @param id unique identifier of the book
     * @return the found book or null if it doesn't exist
     * @throws IllegalArgumentException if the id is null
     */
    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new book in the database.
     * @param book the book to create
     * @return the created book with its generated ID
     * @throws IllegalArgumentException if the book is null or has invalid data
     */
    @Override
    public Book createBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        
        validateBook(book);
        
        // Ensure the book is available by default
        if (book.getAvailable() == null) {
            book.setAvailable(true);
        }
        
        return bookRepository.save(book);
    }

    /**
     * Updates an existing book.
     * @param id identifier of the book to update
     * @param book updated book data
     * @return the updated book or null if not found
     * @throws IllegalArgumentException if parameters are invalid
     */
    @Override
    public Book updateBook(Long id, Book book) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        Optional<Book> existingBookOpt = bookRepository.findById(id);
        if (existingBookOpt.isEmpty()) {
            return null;
        }

        Book existingBook = existingBookOpt.get();
        
        // Validate the new data
        validateBook(book);
        
        // Update the fields
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPrice(book.getPrice());
        existingBook.setStock(book.getStock());
        existingBook.setDescription(book.getDescription());
        existingBook.setAvailable(book.getAvailable());

        return bookRepository.save(existingBook);
    }

    /**
     * Deletes a book from the database.
     * @param id identifier of the book to delete
     * @throws IllegalArgumentException if the id is null
     */
    @Override
    public void deleteBook(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
    }

    /**
     * Validates book data before saving it.
     * @param book the book to validate
     * @throws IllegalArgumentException if the data is invalid
     */
    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Book title is required");
        }
        
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Book author is required");
        }
        
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("Book ISBN is required");
        }
        
        if (book.getPrice() == null || book.getPrice() < 0) {
            throw new IllegalArgumentException("Book price must be greater than or equal to 0");
        }
        
        if (book.getStock() == null || book.getStock() < 0) {
            throw new IllegalArgumentException("Book stock must be greater than or equal to 0");
        }
    }
}