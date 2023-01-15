package com.example.LibraryManagementSystem.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.LibraryManagementSystem.enums.AccountStatus;
import com.example.LibraryManagementSystem.models.Student;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudentCreateRequest {

	@NotNull
	private String name;
	@NotNull
	private long contact;
	@NotNull
	private String email;
	@NotBlank
	private String address;
	private AccountStatus accountStatus;
	
	public Student toStudent()
	{
		return Student.builder()
				.name(name)
				.contact(contact)
				.email(email)
				.address(address)
				.accountStatus(AccountStatus.ACTIVE)
				.build();
	}
	
	
}
