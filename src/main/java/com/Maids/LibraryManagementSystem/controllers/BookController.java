package com.Maids.LibraryManagementSystem.controllers;

import com.Maids.LibraryManagementSystem.dtos.ResponseDto;
import com.Maids.LibraryManagementSystem.models.Book;
import com.Maids.LibraryManagementSystem.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.bookService.getBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long bookId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.bookService.getBook(bookId));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createBook(@Valid @RequestBody Book book) {
        this.bookService.createBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Book created successfully!"));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ResponseDto> updateBook(@PathVariable Long bookId,@Valid @RequestBody Book book) {
        this.bookService.updateBook(bookId, book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Book updated successfully!"));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable Long bookId) {
        this.bookService.deleteBook(bookId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Book deleted successfully!"));
    }
}
