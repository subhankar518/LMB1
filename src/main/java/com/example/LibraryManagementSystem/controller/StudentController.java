package com.example.LibraryManagementSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagementSystem.models.MyUser;
import com.example.LibraryManagementSystem.models.Student;
import com.example.LibraryManagementSystem.request.StudentCreateRequest;
import com.example.LibraryManagementSystem.service.StudentServiceInterf;

@RestController
public class StudentController {
	
	@Autowired
	StudentServiceInterf studentServiceInterf;

	@PostMapping("/createStudent")
	public ResponseEntity createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest)
	{
		return new ResponseEntity(studentServiceInterf.create(studentCreateRequest),HttpStatus.CREATED);
	}
	
	@GetMapping("/student")
	public Student getStudentDetails() throws Exception
	{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		MyUser myUser=(MyUser)authentication.getPrincipal();
		
		if(myUser.getStudent()==null)
			throw new Exception("user is not a student");
		
		return studentServiceInterf.findById(myUser.getId());	
	}
	
	@GetMapping("/studentForAdmin")
	public Student getStudentDetailsForAdmin(@RequestParam("studentId") int studentId) throws Exception
	{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		MyUser myUser=(MyUser)authentication.getPrincipal();
		
		if(myUser.getAdmin()==null)
			throw new Exception("user is a not Admin"); // **** //
		
		return studentServiceInterf.findById(studentId);	
	}
}
