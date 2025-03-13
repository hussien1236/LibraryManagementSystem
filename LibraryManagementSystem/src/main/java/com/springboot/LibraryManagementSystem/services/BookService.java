package com.springboot.LibraryManagementSystem.services;

import com.springboot.LibraryManagementSystem.models.Book;
import com.springboot.LibraryManagementSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }

    public List<Book> getAllBooks(){
      return bookRepository.findAll();
    }
    public Book getBookById(long id){
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }
    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public Book updateBook(long id, Book book){
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setPublicationYear(book.getPublicationYear());
                    existingBook.setIsbn(book.getIsbn());
                    return bookRepository.save(existingBook);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }
    public void deleteBook(long id){
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookRepository.deleteById(id);
    }
}
