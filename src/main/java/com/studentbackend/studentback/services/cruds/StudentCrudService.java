package com.studentbackend.studentback.services.cruds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentbackend.studentback.entities.Student;
import com.studentbackend.studentback.repositories.StudentRepository;

@Service
public class StudentCrudService extends CrudServiceImpl<Student, Long>{

    @Autowired
    public StudentCrudService( StudentRepository repository) {
        super(repository);
    }
    
}
