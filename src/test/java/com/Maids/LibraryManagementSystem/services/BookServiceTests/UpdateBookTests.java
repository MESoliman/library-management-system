package com.Maids.LibraryManagementSystem.services.BookServiceTests;

import com.Maids.LibraryManagementSystem.customExceptions.BusinessException;
import com.Maids.LibraryManagementSystem.models.Book;
import com.Maids.LibraryManagementSystem.repositories.BookRepository;
import com.Maids.LibraryManagementSystem.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateBookTests {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testUpdateBookSuccess() {
        Long bookId = 1L;
        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setIsbn("1234567890123");

        Book updatedBook = new Book();
        updatedBook.setIsbn("9876543210987");
        updatedBook.setTitle("New Title");
        updatedBook.setAuthor("New Author");
        updatedBook.setPublicationYear(2024);
        updatedBook.setAvailable(true);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.existsByIsbnAndIdNot(updatedBook.getIsbn(), bookId)).thenReturn(false);

        assertDoesNotThrow(() -> bookService.updateBook(bookId, updatedBook));

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testUpdateBookIsbnConflict() {
        Long bookId = 1L;
        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setIsbn("1234567890123");

        Book updatedBook = new Book();
        updatedBook.setIsbn("9876543210987");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.existsByIsbnAndIdNot(updatedBook.getIsbn(), bookId)).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> bookService.updateBook(bookId, updatedBook));
        assertEquals("A book with this ISBN already exists.", exception.getMessage());
    }

    @Test
    public void testUpdateBookNotFound() {
        Long bookId = 1L;
        Book updatedBook = new Book();
        updatedBook.setIsbn("9876543210987");

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> bookService.updateBook(bookId, updatedBook));
    }
}
