package com.Maids.LibraryManagementSystem.controllers;

import com.Maids.LibraryManagementSystem.dtos.ResponseDto;
import com.Maids.LibraryManagementSystem.services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/borrowingRecords", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseDto> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        this.borrowingRecordService.borrowBook(bookId, patronId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Book borrowed successfully."));
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseDto> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        this.borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Book returned successfully."));
    }
}
