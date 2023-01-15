package com.example.LibraryManagementSystem.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.example.LibraryManagementSystem.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(value = EnumType.STRING)
	private TransactionType transactionType;
	
	private double fees;
	
	private String uniqueTransactinID;
	
	@ManyToOne
	@JsonIgnoreProperties({"transactionList","booklist"})
	private Student student;
	
	@ManyToOne
	@JsonIgnoreProperties({"transactionList","student"})
	private Book book;  
	
	@CreationTimestamp
	private Date createdOn;
}
