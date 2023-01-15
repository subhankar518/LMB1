package com.example.LibraryManagementSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagementSystem.enums.BookFilterType;
import com.example.LibraryManagementSystem.models.Book;
import com.example.LibraryManagementSystem.request.BookCreateRequest;
import com.example.LibraryManagementSystem.service.BookServiceInterf;

@RestController
public class BookController {
	
	@Autowired
	BookServiceInterf bookServiceInterf;
	
	@PostMapping("/saveBook")
	public ResponseEntity saveBook(@Valid @RequestBody BookCreateRequest bookCreateRequest)
	{
		return new ResponseEntity(bookServiceInterf.saveBook(bookCreateRequest),HttpStatus.CREATED);
	}

	@GetMapping("/searchBook")
	public List<Book> searchBook(@RequestParam("filter") BookFilterType bookFilterType,
			                     @RequestParam("value") String value)
	{
		return bookServiceInterf.searchBook(bookFilterType,value);
	}

/*	
	@GetMapping("/searchBook")
	public List<BookSearchResponse> searchBook2(@RequestParam("filter") BookSearchResponse bookSearchResponse,
			                                    @RequestParam("value") String value)
	{
		return bookServiceInterf.searchBook2(bookSearchResponse,value);
	}
	
*/
	
}