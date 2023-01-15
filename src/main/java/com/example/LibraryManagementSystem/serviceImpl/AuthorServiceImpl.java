package com.example.LibraryManagementSystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.models.Author;
import com.example.LibraryManagementSystem.repository.AuthorRepositoryInterf;
import com.example.LibraryManagementSystem.service.AuthorServiceInterf;

@Service
public class AuthorServiceImpl implements AuthorServiceInterf {

	@Autowired
	AuthorRepositoryInterf authorRepositoryInterf;
	
	@Override
	public Author findByEmail(String email) {
		
		 return authorRepositoryInterf.findByEmail(email);
	}

	@Override
	public Author saveAuthor(Author author) {
		
		return authorRepositoryInterf.save(author);
	}

}
