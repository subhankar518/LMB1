package com.example.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagementSystem.models.Author;

@Repository
public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {

	Author findByEmail(String email);

	Author getAuthorByEmail(String email);


}
