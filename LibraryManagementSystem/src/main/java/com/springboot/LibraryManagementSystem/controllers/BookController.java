package com.springboot.LibraryManagementSystem.controllers;

import com.springboot.LibraryManagementSystem.models.Book;
import com.springboot.LibraryManagementSystem.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public ResponseEntity<List<Book>> GetAllBooks(){
    return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PostMapping(value = "/hello", consumes = "application/json")
    public String sayHello(@RequestBody Book book){
        return "Hello world";
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        System.out.println("----------------------------");
        Book createdBook =bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
