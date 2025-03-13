package com.springboot.LibraryManagementSystem.repositories;

import com.springboot.LibraryManagementSystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

