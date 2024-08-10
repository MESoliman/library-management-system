package com.Maids.LibraryManagementSystem.services.BookServiceTests;

import com.Maids.LibraryManagementSystem.customExceptions.BusinessException;
import com.Maids.LibraryManagementSystem.models.Book;
import com.Maids.LibraryManagementSystem.repositories.BookRepository;
import com.Maids.LibraryManagementSystem.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateBookTests {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;
    @Test
    public void testCreateBookSuccess() {
        Book book = new Book();
        book.setIsbn("1234567890123");

        when(bookRepository.existsByIsbn(anyString())).thenReturn(false);

        bookService.createBook(book);

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testCreateBookIsbnConflict() {
        Book book = new Book();
        book.setIsbn("1234567890123");

        when(bookRepository.existsByIsbn(anyString())).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> bookService.createBook(book));
        assertEquals("A book with this ISBN already exists.", exception.getMessage());
    }

}
