package com.example.LibraryManagementSystem.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.enums.BookFilterType;
import com.example.LibraryManagementSystem.models.Author;
import com.example.LibraryManagementSystem.models.Book;
import com.example.LibraryManagementSystem.repository.BookRepositoryInterf;
import com.example.LibraryManagementSystem.request.BookCreateRequest;
import com.example.LibraryManagementSystem.service.AuthorServiceInterf;
import com.example.LibraryManagementSystem.service.BookServiceInterf;

@Service
public class BookServiceImpl implements BookServiceInterf {
	
	@Autowired
	BookRepositoryInterf bookRepositoryInterf;
	
	@Autowired
	AuthorServiceInterf authorServiceInterf;

	@Override
	public Book saveBook(BookCreateRequest bookCreateRequest) {
		Book book=bookCreateRequest.toBook();
		
		Author author=book.getAuthor();
		
		Author variableFromDb=authorServiceInterf.findByEmail(author.getEmail());
		
		if(variableFromDb== null)
		{
			variableFromDb= authorServiceInterf.saveAuthor(author);
		}
		book.setAuthor(variableFromDb);
		return bookRepositoryInterf.save(book);
	}

	@Override
	public List<Book> searchBook(BookFilterType bookFilterType, String value) {
		switch(bookFilterType)
		{
			case NAME:
			{
				return bookRepositoryInterf.findByName(value);
			}
			case AUTHOR_NAME:
			{
				return bookRepositoryInterf.findByAuthorName(value);
			}
			case COST:
			{
				return bookRepositoryInterf.findByCost(value);
			}
			case CATEGORY:   // category case is not working because its enum out of enum.
			{
				return bookRepositoryInterf.findByCategory(value);
			}
			case ID:
			{
				return bookRepositoryInterf.findAllById(Collections.singleton(Integer.parseInt(value)));
			}
			default:
				return new ArrayList<Book>();
			
		}
	}

	@Override
	public void save(Book book) {		
		bookRepositoryInterf.save(book);
	}

	@Override
	public Book findById(int bookId) {
		return bookRepositoryInterf.findById(bookId).get();
	}


}
