package com.Maids.LibraryManagementSystem.services;

import com.Maids.LibraryManagementSystem.customExceptions.BusinessException;
import com.Maids.LibraryManagementSystem.models.Book;
import com.Maids.LibraryManagementSystem.models.BorrowingRecord;
import com.Maids.LibraryManagementSystem.models.Patron;
import com.Maids.LibraryManagementSystem.repositories.BookRepository;
import com.Maids.LibraryManagementSystem.repositories.BorrowingRecordRepository;
import com.Maids.LibraryManagementSystem.repositories.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;

    @Transactional
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Book with ID " + bookId + " not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new NoSuchElementException("Patron with ID " + patronId + " not found"));

        if (book.isAvailable()) {
            BorrowingRecord record = new BorrowingRecord();
            record.setBook(book);
            record.setPatron(patron);
            record.setBorrowingDate(LocalDate.now());

            book.setAvailable(false);
            borrowingRecordRepository.save(record);
            bookRepository.save(book);
        } else {
            throw new BusinessException("Book with ID " + bookId + " is currently unavailable");
        }
    }

    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new NoSuchElementException("No active borrowing record found for book ID " + bookId + " and patron ID " + patronId));

        record.setReturnDate(LocalDate.now());
        record.getBook().setAvailable(true);

        borrowingRecordRepository.save(record);
        bookRepository.save(record.getBook());
    }
}
