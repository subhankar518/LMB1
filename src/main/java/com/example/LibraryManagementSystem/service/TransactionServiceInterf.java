package com.example.LibraryManagementSystem.service;

import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.Exception.TransactionServiceException;
import com.example.LibraryManagementSystem.models.Transaction;

@Service
public interface TransactionServiceInterf {

	Transaction issueBook(int studentId, int bookId) throws TransactionServiceException;

	String returnBook(int studentId, int bookId) throws TransactionServiceException;

}
