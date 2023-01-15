package com.example.LibraryManagementSystem.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.LibraryManagementSystem.enums.Category;
import com.example.LibraryManagementSystem.models.Author;
import com.example.LibraryManagementSystem.models.Book;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookCreateRequest {

	@NotBlank
	private String name;
	@Positive
	private int cost;
	@NotNull
	private Category category;
	@NotNull
	private Author author;
	
	public Book toBook()
	{
		return Book.builder()
				.name(this.name)
				.cost(this.cost)
				.category(this.category)
				.author(this.author)
				.build();
	}
}
