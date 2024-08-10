package com.Maids.LibraryManagementSystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class BorrowingRecord extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "book_id", insertable = true, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", insertable = true, updatable = false)
    private Patron patron;

    private LocalDate borrowingDate;
    private LocalDate returnDate;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
