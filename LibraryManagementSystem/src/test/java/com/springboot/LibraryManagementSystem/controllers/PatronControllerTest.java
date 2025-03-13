package com.springboot.LibraryManagementSystem.controllers;

import com.springboot.LibraryManagementSystem.models.Patron;
import com.springboot.LibraryManagementSystem.services.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    private MockMvc mockMvc;
    @BeforeEach
    void setup() {
     mockMvc = MockMvcBuilders.standaloneSetup(patronController).build();
    }

    @Test
    void shouldCreatePatronSuccessfully() throws Exception {
        Patron patron = new Patron(1L, "John Doe", "john.doe@example.com", "123-456-7890");

        when(patronService.addPatron(any(Patron.class))).thenReturn(patron);

        mockMvc.perform(post("/api/patrons")
                        .contentType("application/json")
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"phone\":\"123-456-7890\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNb").value("123-456-7890"));

        verify(patronService).addPatron(any(Patron.class));
    }

    @Test
    void shouldReturnPatronById() throws Exception {
        Patron patron = new Patron(1L, "Jane Doe", "jane.doe@example.com", "987-654-3210");

        when(patronService.getPatronById(1L)).thenReturn(patron);

        mockMvc.perform(get("/api/patrons/1"))
                .andExpect(status().isOk())  // Expecting HTTP 200 OK
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$.phoneNb").value("987-654-3210"));

        verify(patronService).getPatronById(1L);
    }

    @Test
    void shouldUpdatePatronSuccessfully() throws Exception {
        Patron updatedPatron = new Patron(1L, "Updated Name", "updated.email@example.com", "555-555-5555");

        when(patronService.updatePatron(eq(1L), any(Patron.class))).thenReturn(updatedPatron);

        mockMvc.perform(put("/api/patrons/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Updated Name\",\"email\":\"updated.email@example.com\",\"phone\":\"555-555-5555\"}"))
                .andExpect(status().isOk())  // Expecting HTTP 200 OK
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated.email@example.com"))
                .andExpect(jsonPath("$.phoneNb").value("555-555-5555"));

        verify(patronService).updatePatron(eq(1L), any(Patron.class));
    }

    @Test
    void shouldDeletePatronSuccessfully() throws Exception {
        doNothing().when(patronService).deletePatron(1L);

        mockMvc.perform(delete("/api/patrons/1"))
                .andExpect(status().isNoContent());

        verify(patronService).deletePatron(1L);
    }
}
