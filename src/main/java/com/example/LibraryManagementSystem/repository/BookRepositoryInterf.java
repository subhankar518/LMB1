package com.example.LibraryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryManagementSystem.models.Book;

public interface BookRepositoryInterf extends JpaRepository<Book, Integer> {

	List<Book> findByName(String value);
	
	List<Book> findByAuthorName(String value);
	
	List<Book> findByCost(String value);
	
	List<Book> findByCategory(String value);

}
