package com.example.LibraryManagementSystem.response;

import java.util.List;

import com.example.LibraryManagementSystem.enums.Category;
import com.example.LibraryManagementSystem.models.Author;
import com.example.LibraryManagementSystem.models.Student;
import com.example.LibraryManagementSystem.models.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookSearchResponse {

	private int id;
	private String name;
	private int cost;
	private Category category;
	
	@JsonIgnoreProperties({"bookList"})
	private Author author;
	private Student student;
	private List<Transaction> transactionList;
}
