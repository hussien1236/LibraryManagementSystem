package com.springboot.LibraryManagementSystem.services;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.springboot.LibraryManagementSystem.models.Book;
import com.springboot.LibraryManagementSystem.models.BorrowingRecord;
import com.springboot.LibraryManagementSystem.models.Patron;
import com.springboot.LibraryManagementSystem.repositories.BookRepository;
import com.springboot.LibraryManagementSystem.repositories.BorrowingRecordRepository;
import com.springboot.LibraryManagementSystem.repositories.PatronRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BorrowingServiceTest {

    @InjectMocks
    private BorrowingRecordService borrowingService;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @Test
    void shouldBorrowBookSuccessfully() {
        Book book = new Book(1L, "The Pragmatic Programmer", "Andy Hunt", 1999, "9780201616224");
        Patron patron = new Patron(1L, "John Doe", "john@example.com","009612345");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BorrowingRecord result = borrowingService.borrowBook(1L, 1L);

        assertNotNull(result);
        assertEquals("The Pragmatic Programmer", result.getBook().getTitle());
        assertEquals("John Doe", result.getPatron().getName());

        verify(bookRepository).findById(1L);
        verify(patronRepository).findById(1L);
        verify(borrowingRecordRepository).save(any(BorrowingRecord.class));
    }

    @Test
    void shouldReturnBookSuccessfully() {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(1L);
        record.setBorrowDate(new Date());

        when(borrowingRecordRepository.findById(1L)).thenReturn(Optional.of(record));

        borrowingService.returnBook(1L,1L);

        assertNotNull(record.getReturnDate());
        verify(borrowingRecordRepository).save(record);
    }
}

