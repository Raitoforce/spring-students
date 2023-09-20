package com.studentbackend.studentback.controllers.implementations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentbackend.studentback.entities.Student;
import com.studentbackend.studentback.services.cruds.StudentCrudService;

@SpringBootTest
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentCrudService studentCrudService;

    @BeforeEach
    public void setUp() {        
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentController(studentCrudService)).build();
    }

    @Test
    public void testFetchAll() throws Exception {
        // Create a list of students for testing
        List<Student> students = Arrays.asList(
            new Student(1L, "John", "Segura", "Jonh@segura.com"),
            new Student(2L, "Alice", "Segura", "Alice@segura.com")
        );

        when(studentCrudService.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("John"))
            .andExpect(jsonPath("$[1].name").value("Alice"));
    }

    @Test
    public void testFetchById() throws Exception {
        Long studentId = 1L;
        Student student = new Student(studentId, "John", "Segura", "Jonh@segura.com");

        // Mock the behavior of the studentCrudService
        when(studentCrudService.findById(studentId)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.get("/students/{id}", studentId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(student.getName()))
            .andExpect(jsonPath("$.lastName").value(student.getLastName()))
            .andExpect(jsonPath("$.email").value(student.getEmail()));

    }

    @Test
    public void testSave() throws Exception {
        Student newStudent = new Student(1L, "Alice", "Segura", "Alice@segura.com");

        when(studentCrudService.save(any(Student.class))).thenReturn(newStudent);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content(converToJson(newStudent)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Alice"))
            .andExpect(jsonPath("$.lastName").value("Segura"))
            .andExpect(jsonPath("$.email").value("Alice@segura.com"));
    }

    @Test
    public void testUpdate() throws Exception {
        Long studentId = 1L;
        Student updatedStudent = new Student(studentId, "Alice", "Segura", "Alice@segura.com");

        when(studentCrudService.update(any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(MockMvcRequestBuilders.put("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content(converToJson(updatedStudent)))  
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Alice"))
            .andExpect(jsonPath("$.lastName").value("Segura"))
            .andExpect(jsonPath("$.email").value("Alice@segura.com"))
            .andExpect(jsonPath("$.id").value(studentId));
    }

     @Test
    void testDelete() throws Exception {
        Long studentId = 1L;
        Student student = new Student(studentId, "John", "Segura", "Jonh@segura.com");

        when(studentCrudService.findById(studentId)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.delete("/students/{id}", studentId))
               .andExpect(status().isOk());

        verify(studentCrudService, times(1)).delete(studentId);

    }

    private String converToJson(Student student) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
