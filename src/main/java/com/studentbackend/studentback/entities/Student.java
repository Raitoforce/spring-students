package com.studentbackend.studentback.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "students")
public class Student implements Serializable {
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must be required")
    private String name;

    @NotBlank(message = "LastName must be required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
}
