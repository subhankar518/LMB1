package com.example.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagementSystem.models.MyUser;

@Repository
public interface MyUserRepositoryInterf extends JpaRepository<MyUser, Integer> {

	MyUser findByUsername(String username);

}
