package com.studentbackend.studentback.controllers.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentbackend.studentback.entities.Student;
import com.studentbackend.studentback.services.cruds.StudentCrudService;

import jakarta.validation.Valid;

@RequestMapping("/students")
@RestController
public class StudentController extends Controller<Student, Long> {

    @Autowired
    StudentController(StudentCrudService crudService) {
        super(crudService);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Student>> fetchAll() {
        return super.fetchAll();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Student> fetchById(@PathVariable Long id) {
        return super.fetchById(id);
    }

    @PostMapping
    @Override
    public ResponseEntity<Student> save(@Valid @RequestBody Student entity) {
        return super.save(entity);
    }

    @PutMapping
    @Override
    public ResponseEntity<Student> update(@Valid @RequestBody Student entity) {
        return super.update(entity);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    
}
