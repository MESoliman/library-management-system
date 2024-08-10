package com.Maids.LibraryManagementSystem.controllers;

import com.Maids.LibraryManagementSystem.dtos.ResponseDto;
import com.Maids.LibraryManagementSystem.models.Patron;
import com.Maids.LibraryManagementSystem.services.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/patrons", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping("")
    public ResponseEntity<List<Patron>> getPatrons() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.patronService.getPatrons());
    }

    @GetMapping("/{patronId}")
    public ResponseEntity<Patron> getPatron(@PathVariable Long patronId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.patronService.getPatron(patronId));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createPatron(@Valid @RequestBody Patron patron) {
        this.patronService.createPatron(patron);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Patron created successfully!"));
    }

    @PutMapping("/{patronId}")
    public ResponseEntity<ResponseDto> updatePatron(@PathVariable Long patronId,@Valid @RequestBody Patron patron) {
        this.patronService.updatePatron(patronId, patron);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Patron updated successfully!"));
    }

    @DeleteMapping("/{patronId}")
    public ResponseEntity<ResponseDto> deletePatron(@PathVariable Long patronId) {
        this.patronService.deletePatron(patronId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Patron deleted successfully!"));
    }
}
