package com.springboot.LibraryManagementSystem.services;

import com.springboot.LibraryManagementSystem.models.Book;
import com.springboot.LibraryManagementSystem.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldSaveBookSuccessfully() {
        Book book = new Book(1L, "The Pragmatic Programmer", "Andy Hunt", 1999, "9780201616224");
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book savedBook = bookService.addBook(book);

        assertNotNull(savedBook);
        assertEquals("The Pragmatic Programmer", savedBook.getTitle());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void shouldFindBookById() {
        Book book = new Book(1L, "Clean Code", "Robert C. Martin", 2008, "9780132350884");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Book foundBook = bookService.getBookById(1L);

        assertNotNull(foundBook);
        assertEquals("Clean Code", foundBook.getTitle());
        verify(bookRepository).findById(1L);
    }
    @Test
    void shouldUpdateBookSuccessfully() {
        Book oldBook = new Book(1L, "Old Title", "Old Author", 2020, "9780201616225");
        Book updatedBook = new Book(1L, "New Title", "New Author", 2020, "9780201616225");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(oldBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Author", result.getAuthor());
        verify(bookRepository).save(any(Book.class));
    }
    @Test
    void shouldDeleteBookById() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository).deleteById(1L);
    }

    @Test
    void shouldReturnAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "The Pragmatic Programmer", "Andy Hunt", 1999, "9780201616224"),
                new Book(2L, "Clean Code", "Robert C. Martin", 2008, "9780132350884")
        );
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookRepository).findAll();
    }
}

