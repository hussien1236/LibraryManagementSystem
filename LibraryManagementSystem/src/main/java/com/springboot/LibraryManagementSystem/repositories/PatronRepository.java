package com.springboot.LibraryManagementSystem.repositories;

import com.springboot.LibraryManagementSystem.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}