package com.springboot.LibraryManagementSystem.controllers;

import com.springboot.LibraryManagementSystem.models.Book;
import com.springboot.LibraryManagementSystem.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build(); // Ensure it's initialized after @InjectMocks
    }

    @Test
    void shouldCreateBookSuccessfully() throws Exception {
        Book book = new Book(1L, "Clean Code", "Robert C. Martin", 2008, "9780132350884");

        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content("{\"title\":\"Clean Code\",\"author\":\"Robert C. Martin\",\"year\":2008,\"isbn\":\"9780132350884\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"))
                .andExpect(jsonPath("$.publicationYear").value(2008))
                .andExpect(jsonPath("$.isbn").value("9780132350884"));

        verify(bookService).addBook(any(Book.class));
    }

    @Test
    void shouldReturnBookById() throws Exception {
        Book book = new Book(1L, "The Pragmatic Programmer", "Andy Hunt", 1999, "9780201616224");

        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Pragmatic Programmer"))
                .andExpect(jsonPath("$.author").value("Andy Hunt"))
                .andExpect(jsonPath("$.publicationYear").value(1999))
                .andExpect(jsonPath("$.isbn").value("9780201616224"));

        verify(bookService).getBookById(1L);
    }


    @Test
    void shouldUpdateBookSuccessfully() throws Exception {
        Book updatedBook = new Book(1L, "Updated Book Title", "Updated Author", 2020, "9780201616225");

        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/api/books/1")
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Book Title\",\"author\":\"Updated Author\",\"year\":2020,\"isbn\":\"9780201616225\"}"))
                .andExpect(status().isOk())  // Expecting HTTP 200 OK
                .andExpect(jsonPath("$.title").value("Updated Book Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"))
                .andExpect(jsonPath("$.publicationYear").value(2020))
                .andExpect(jsonPath("$.isbn").value("9780201616225"));

        verify(bookService).updateBook(eq(1L), any(Book.class));
    }

    @Test
    void shouldDeleteBookSuccessfully() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService).deleteBook(1L);
    }
}

