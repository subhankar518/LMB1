package com.example.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagementSystem.enums.TransactionType;
import com.example.LibraryManagementSystem.models.Book;
import com.example.LibraryManagementSystem.models.Student;
import com.example.LibraryManagementSystem.models.Transaction;

@Repository
public interface TransactionRepositoryInterf extends JpaRepository<Transaction, Integer> {

	Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student,
			TransactionType issue);

	

}
