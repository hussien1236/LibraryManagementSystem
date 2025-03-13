package com.springboot.LibraryManagementSystem.services;

import com.springboot.LibraryManagementSystem.models.Patron;
import com.springboot.LibraryManagementSystem.repositories.PatronRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found"));
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron updatedPatron) {
        return patronRepository.findById(id).map(patron -> {
            patron.setName(updatedPatron.getName());
            patron.setEmail(updatedPatron.getEmail());
            patron.setPhoneNb(updatedPatron.getPhoneNb());
            return patronRepository.save(patron);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found"));
    }

    public void deletePatron(Long id) {
        if (!patronRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found");
        }
        patronRepository.deleteById(id);
    }
}

