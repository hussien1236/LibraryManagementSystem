package com.springboot.LibraryManagementSystem.controllers;

import com.springboot.LibraryManagementSystem.models.BorrowingRecord;
import com.springboot.LibraryManagementSystem.models.Book;
import com.springboot.LibraryManagementSystem.models.Patron;
import com.springboot.LibraryManagementSystem.services.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BorrowingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @Mock
    private Book book;

    @Mock
    private Patron patron;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(borrowingRecordController).build();
    }

    @Test
    void testBorrowBook() throws Exception {
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book(bookId, "Clean Code", "Robert C. Martin", 2008, "9780132350884");
        Patron patron = new Patron(patronId, "John Doe", "johndoe@example.com", "009612345");

        BorrowingRecord borrowingRecord = new BorrowingRecord(book, patron);

        when(borrowingRecordService.borrowBook(bookId, patronId)).thenReturn(borrowingRecord);

        mockMvc.perform(post("/api/borrow/{bookId}/patron/{patronId}", bookId, patronId))
                .andExpect(status().isOk());

        verify(borrowingRecordService, times(1)).borrowBook(bookId, patronId);
    }

    @Test
    void testReturnBook() throws Exception {
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book(bookId, "Clean Code", "Robert C. Martin", 2008, "9780132350884");
        Patron patron = new Patron(patronId, "John Doe", "johndoe@example.com","009612345");

        BorrowingRecord borrowingRecord = new BorrowingRecord(book, patron);

        when(borrowingRecordService.returnBook(bookId, patronId)).thenReturn(borrowingRecord);

        mockMvc.perform(put("/api/borrow/{bookId}/patron/{patronId}/return", bookId, patronId))
                .andExpect(status().isOk());

        verify(borrowingRecordService, times(1)).returnBook(bookId, patronId);
    }


}
