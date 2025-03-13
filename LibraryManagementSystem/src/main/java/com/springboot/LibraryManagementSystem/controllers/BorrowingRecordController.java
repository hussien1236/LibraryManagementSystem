package com.springboot.LibraryManagementSystem.controllers;

import com.springboot.LibraryManagementSystem.models.BorrowingRecord;
import com.springboot.LibraryManagementSystem.services.BorrowingRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return ResponseEntity.ok(borrowingRecordService.borrowBook(bookId, patronId));
    }

    @PutMapping("/{bookId}/patron/{patronId}/return")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return ResponseEntity.ok(borrowingRecordService.returnBook(bookId, patronId));
    }
}

