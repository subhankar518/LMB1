package com.example.LibraryManagementSystem.request;

import com.example.LibraryManagementSystem.models.Admin;
import com.example.LibraryManagementSystem.models.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

	private String username;
	private String password;
	private String authority;
	
	private Student student;
	
	private Admin admin;
}
