package com.springboot.LibraryManagementSystem.services;

import com.springboot.LibraryManagementSystem.models.Patron;
import com.springboot.LibraryManagementSystem.repositories.PatronRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    @Test
    void shouldSavePatronSuccessfully() {
        Patron patron = new Patron(1L, "John Doe", "john.doe@example.com", "123-456-7890");
        when(patronRepository.save(any(Patron.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Patron savedPatron = patronService.addPatron(patron);

        assertNotNull(savedPatron);
        assertEquals("John Doe", savedPatron.getName());
        assertEquals("john.doe@example.com", savedPatron.getEmail());
        assertEquals("123-456-7890", savedPatron.getPhoneNb());
        verify(patronRepository).save(any(Patron.class));
    }

    @Test
    void shouldFindPatronById() {
        Patron patron = new Patron(1L, "Jane Doe", "jane.doe@example.com", "987-654-3210");
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

        Patron foundPatron = patronService.getPatronById(1L);

        assertNotNull(foundPatron);
        assertEquals("Jane Doe", foundPatron.getName());
        assertEquals("jane.doe@example.com", foundPatron.getEmail());
        assertEquals("987-654-3210", foundPatron.getPhoneNb());
        verify(patronRepository).findById(1L);
    }

    @Test
    void shouldUpdatePatronSuccessfully() {
        Patron oldPatron = new Patron(1L, "Old Name", "old.email@example.com", "555-555-5555");
        Patron updatedPatron = new Patron(1L, "Updated Name", "updated.email@example.com", "555-555-5555");

        when(patronRepository.findById(1L)).thenReturn(Optional.of(oldPatron));
        when(patronRepository.save(any(Patron.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Patron result = patronService.updatePatron(1L, updatedPatron);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals("updated.email@example.com", result.getEmail());
        assertEquals("555-555-5555", result.getPhoneNb());
        verify(patronRepository).findById(1L);
    }

    @Test
    void shouldDeletePatronById() {
        when(patronRepository.existsById(1L)).thenReturn(true);
        doNothing().when(patronRepository).deleteById(1L);

        patronService.deletePatron(1L);

        verify(patronRepository).deleteById(1L);
    }

    @Test
    void shouldReturnAllPatrons() {
        List<Patron> patrons = Arrays.asList(
                new Patron(1L, "John Doe", "john.doe@example.com", "123-456-7890"),
                new Patron(2L, "Jane Doe", "jane.doe@example.com", "987-654-3210")
        );
        when(patronRepository.findAll()).thenReturn(patrons);

        List<Patron> result = patronService.getAllPatrons();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(patronRepository).findAll();
    }

}
