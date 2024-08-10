package com.Maids.LibraryManagementSystem.services.BorrowingRecordServiceTests;

import com.Maids.LibraryManagementSystem.customExceptions.BusinessException;
import com.Maids.LibraryManagementSystem.models.Book;
import com.Maids.LibraryManagementSystem.models.BorrowingRecord;
import com.Maids.LibraryManagementSystem.models.Patron;
import com.Maids.LibraryManagementSystem.repositories.BookRepository;
import com.Maids.LibraryManagementSystem.repositories.BorrowingRecordRepository;
import com.Maids.LibraryManagementSystem.repositories.PatronRepository;
import com.Maids.LibraryManagementSystem.services.BookService;
import com.Maids.LibraryManagementSystem.services.BorrowingRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BorrowBookTests {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @InjectMocks
    private BorrowingRecordService borrowingRecordService;

    @Test
    public void testBorrowBookSuccess() {
        Long bookId = 1L;
        Long patronId = 1L;

        Book book = new Book();
        book.setId(bookId);
        book.setAvailable(true);

        Patron patron = new Patron();
        patron.setId(patronId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

        borrowingRecordService.borrowBook(bookId, patronId);

        verify(borrowingRecordRepository, times(1)).save(any(BorrowingRecord.class));
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testBorrowBookBookNotFound() {
        Long bookId = 1L;
        Long patronId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(new Patron()));

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> borrowingRecordService.borrowBook(bookId, patronId));
        assertEquals("Book with ID " + bookId + " not found", exception.getMessage());
    }

    @Test
    public void testBorrowBookPatronNotFound() {
        Long bookId = 1L;
        Long patronId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(new Book()));
        when(patronRepository.findById(patronId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> borrowingRecordService.borrowBook(bookId, patronId));
        assertEquals("Patron with ID " + patronId + " not found", exception.getMessage());
    }

    @Test
    public void testBorrowBookUnavailable() {
        Long bookId = 1L;
        Long patronId = 1L;

        Book book = new Book();
        book.setId(bookId);
        book.setAvailable(false);

        Patron patron = new Patron();
        patron.setId(patronId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

        BusinessException exception = assertThrows(BusinessException.class, () -> borrowingRecordService.borrowBook(bookId, patronId));
        assertEquals("Book with ID " + bookId + " is currently unavailable", exception.getMessage());
    }
}
