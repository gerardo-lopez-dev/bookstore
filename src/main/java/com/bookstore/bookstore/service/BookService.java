package com.bookstore.bookstore.service;

import java.util.List;
import com.bookstore.bookstore.model.Book;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}
