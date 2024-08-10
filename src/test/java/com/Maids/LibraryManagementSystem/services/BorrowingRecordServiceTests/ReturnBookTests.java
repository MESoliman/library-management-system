package com.Maids.LibraryManagementSystem.services.BorrowingRecordServiceTests;

import com.Maids.LibraryManagementSystem.models.Book;
import com.Maids.LibraryManagementSystem.models.BorrowingRecord;
import com.Maids.LibraryManagementSystem.models.Patron;
import com.Maids.LibraryManagementSystem.repositories.BookRepository;
import com.Maids.LibraryManagementSystem.repositories.BorrowingRecordRepository;
import com.Maids.LibraryManagementSystem.services.BorrowingRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReturnBookTests {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @InjectMocks
    private BorrowingRecordService borrowingRecordService;

    @Test
    public void testReturnBookSuccess() {
        Long bookId = 1L;
        Long patronId = 1L;

        Book book = new Book();
        book.setId(bookId);
        book.setAvailable(false);

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(new Patron());
        record.setBorrowingDate(LocalDate.now().minusDays(1));
        record.setReturnDate(null);

        when(borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId))
                .thenReturn(Optional.of(record));

        borrowingRecordService.returnBook(bookId, patronId);

        verify(borrowingRecordRepository, times(1)).save(record);
        verify(bookRepository, times(1)).save(book);
        assertNotNull(record.getReturnDate());
        assertTrue(book.isAvailable());
    }

    @Test
    public void testReturnBookNoActiveBorrowingRecord() {
        Long bookId = 1L;
        Long patronId = 1L;

        when(borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId))
                .thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> borrowingRecordService.returnBook(bookId, patronId));
        assertEquals("No active borrowing record found for book ID " + bookId + " and patron ID " + patronId, exception.getMessage());
    }
}
