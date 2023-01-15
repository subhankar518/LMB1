package com.example.LibraryManagementSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.enums.BookFilterType;
import com.example.LibraryManagementSystem.models.Book;
import com.example.LibraryManagementSystem.request.BookCreateRequest;

@Service
public interface BookServiceInterf {

	Book saveBook(BookCreateRequest bookCreateRequest);

	List<Book> searchBook(BookFilterType bookFilterType, String value);

	void save(Book book);

	Book findById(int bookId);

//	List<BookSearchResponse> searchBook2(BookSearchResponse bookSearchResponse, String value);

}

