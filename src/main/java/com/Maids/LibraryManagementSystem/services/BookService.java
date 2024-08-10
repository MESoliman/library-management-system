package com.Maids.LibraryManagementSystem.services;

import com.Maids.LibraryManagementSystem.customExceptions.BusinessException;
import com.Maids.LibraryManagementSystem.models.Book;
import com.Maids.LibraryManagementSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public Book getBook(Long bookId) {
        return this.bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
    }

    public void createBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BusinessException("A book with this ISBN already exists.");
        }
        this.bookRepository.save(book);
    }

    public void updateBook(Long bookId, Book book) {
        Book existingBook = this.getBook(bookId);
        if (bookRepository.existsByIsbnAndIdNot(book.getIsbn(), existingBook.getId())) {
            throw new BusinessException("A book with this ISBN already exists.");
        }
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublicationYear(book.getPublicationYear());
        this.bookRepository.save(existingBook);
    }

    public void deleteBook(Long bookId) {
        Book book = this.getBook(bookId);
        this.bookRepository.delete(book);
    }
}
