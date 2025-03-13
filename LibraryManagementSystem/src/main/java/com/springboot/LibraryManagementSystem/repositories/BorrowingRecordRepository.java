package com.springboot.LibraryManagementSystem.repositories;

import com.springboot.LibraryManagementSystem.models.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
}

