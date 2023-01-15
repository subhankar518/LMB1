package com.example.LibraryManagementSystem.service;

import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.models.Author;

@Service
public interface AuthorServiceInterf {

	Author findByEmail(String email);

	Author saveAuthor(Author author);

}
