package com.Maids.LibraryManagementSystem.services;

import com.Maids.LibraryManagementSystem.models.Patron;
import com.Maids.LibraryManagementSystem.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getPatrons() {
        return this.patronRepository.findAll();
    }

    public Patron getPatron(Long patronId) {
        return this.patronRepository.findById(patronId).orElseThrow(NoSuchElementException::new);
    }

    public void createPatron(Patron patron) {
        this.patronRepository.save(patron);
    }

    public void updatePatron(Long patronId, Patron patron) {
        Patron existingPatron = this.getPatron(patronId);
        existingPatron.setName(patron.getName());
        existingPatron.setEmail(patron.getEmail());
        existingPatron.setPhoneNumber(patron.getPhoneNumber());
        existingPatron.setAddress(patron.getAddress());
        this.patronRepository.save(existingPatron);
    }

    public void deletePatron(Long patronId) {
        Patron patron = this.getPatron(patronId);
        this.patronRepository.delete(patron);
    }
}
