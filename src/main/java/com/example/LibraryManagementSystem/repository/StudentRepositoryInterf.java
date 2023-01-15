package com.example.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagementSystem.models.Student;

@Repository
public interface StudentRepositoryInterf extends JpaRepository<Student, Integer> {

}
