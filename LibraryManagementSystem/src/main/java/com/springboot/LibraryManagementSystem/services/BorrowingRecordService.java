package com.springboot.LibraryManagementSystem.services;

import com.springboot.LibraryManagementSystem.models.Book;
import com.springboot.LibraryManagementSystem.models.BorrowingRecord;
import com.springboot.LibraryManagementSystem.models.Patron;
import com.springboot.LibraryManagementSystem.repositories.BookRepository;
import com.springboot.LibraryManagementSystem.repositories.BorrowingRecordRepository;
import com.springboot.LibraryManagementSystem.repositories.PatronRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository,
                                  BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found"));

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(new Date());

        return borrowingRecordRepository.save(record);
    }

    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findAll().stream()
                .filter(r -> r.getBook().getId().equals(bookId) && r.getPatron().getId().equals(patronId) && r.getReturnDate() == null)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrowing record not found"));

        record.setReturnDate(new Date());
        return borrowingRecordRepository.save(record);
    }
}

