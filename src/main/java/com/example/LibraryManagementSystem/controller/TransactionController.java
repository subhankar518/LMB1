package com.example.LibraryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagementSystem.Exception.TransactionServiceException;
import com.example.LibraryManagementSystem.service.TransactionServiceInterf;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionServiceInterf transactionServiceInterf;

	@PostMapping("/transaction/issue")
	public ResponseEntity issueTransaction(@RequestParam("studentId") int studentId,
			                               @RequestParam("bookId") int bookId) throws TransactionServiceException
	{
		return new ResponseEntity(transactionServiceInterf.issueBook(studentId,bookId),HttpStatus.OK);
	}
	
	@PostMapping("/transaction/return")
	public ResponseEntity returnTransaction(@RequestParam("studentId") int studentId,
                                            @RequestParam("bookId") int bookId) throws TransactionServiceException
	{
		return new ResponseEntity(transactionServiceInterf.returnBook(studentId,bookId),HttpStatus.OK);
	}


}
