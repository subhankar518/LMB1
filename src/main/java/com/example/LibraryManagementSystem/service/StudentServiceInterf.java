package com.example.LibraryManagementSystem.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.models.Student;
import com.example.LibraryManagementSystem.request.StudentCreateRequest;


@Service
public interface StudentServiceInterf {

	Student create(@Valid StudentCreateRequest studentCreateRequest);

	Student findById(int studentId);

}
