package com.studentbackend.studentback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentbackend.studentback.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}