package com.example.LibraryManagementSystem.serviceImpl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.models.Student;
import com.example.LibraryManagementSystem.repository.StudentRepositoryInterf;
import com.example.LibraryManagementSystem.request.StudentCreateRequest;
import com.example.LibraryManagementSystem.service.StudentServiceInterf;


@Service
public class StudentServiceImpl implements StudentServiceInterf {
	
	@Autowired
	StudentRepositoryInterf studentRepositoryInterf;

	@Override
	public Student create(@Valid StudentCreateRequest studentCreateRequest) {
		return studentRepositoryInterf.save(studentCreateRequest.toStudent());
	}

	@Override
	public Student findById(int studentId) {
		return studentRepositoryInterf.findById(studentId).get();
	}
	
	

}
