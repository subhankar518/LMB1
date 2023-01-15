package com.example.LibraryManagementSystem.serviceImpl;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.Exception.TransactionServiceException;
import com.example.LibraryManagementSystem.enums.BookFilterType;
import com.example.LibraryManagementSystem.enums.TransactionType;
import com.example.LibraryManagementSystem.models.Book;
import com.example.LibraryManagementSystem.models.Student;
import com.example.LibraryManagementSystem.models.Transaction;
import com.example.LibraryManagementSystem.repository.TransactionRepositoryInterf;
import com.example.LibraryManagementSystem.service.BookServiceInterf;
import com.example.LibraryManagementSystem.service.StudentServiceInterf;
import com.example.LibraryManagementSystem.service.TransactionServiceInterf;

@Service
public class TransactionServiceImpl implements TransactionServiceInterf {
	
	@Autowired
	StudentServiceInterf studentServiceInterf;
	
	@Autowired
	BookServiceInterf bookServiceInterf;
	
	@Autowired
	TransactionRepositoryInterf transactionRepositoryInterf;
	
	@Value("${book.return.max_days}")
	int max_days;
	

	@Override
	public Transaction issueBook(int studentId, int bookId) throws TransactionServiceException {
		
		Student student= studentServiceInterf.findById(studentId);
		
		if(student==null)
			throw new TransactionServiceException("StudentId is not available");
		
		List<Book> book= bookServiceInterf.searchBook(BookFilterType.ID, String.valueOf(bookId));
		
		if(book==null || book.size() != 1 || book.get(0).getStudent() !=null) // the last condition checks that the book is assigned to any student or not
			throw new TransactionServiceException("bookId is not available");
		
		Transaction transaction= Transaction.builder()
				                    .uniqueTransactinID(UUID.randomUUID().toString())
				                    .transactionType(TransactionType.ISSUE)
				                    .fees(book.get(0).getCost())				          
				                    .student(student)
				                    .book(book.get(0))
		                      		.build();
		
		book.get(0).setStudent(student);   // logic for book gets unavaliable
		bookServiceInterf.save(book.get(0));     
		
		return transactionRepositoryInterf.save(transaction);
	}

	public String returnBook(int studentId, int bookId) throws TransactionServiceException
	{
		Student student=studentServiceInterf.findById(studentId);
		
		if(student==null)
			throw new TransactionServiceException("wrong student Id");
		
		Book book= bookServiceInterf.findById(bookId);
		
		if(book==null) // the last condition checks that the book is assigned to any student or not
			throw new TransactionServiceException("Book Id is not available");
		
		if(book.getStudent().getId() !=studentId)
			throw new TransactionServiceException("Book not issued to the student");
		
		Transaction issueTransaction=transactionRepositoryInterf.findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(book, student, TransactionType.ISSUE);

		
		Transaction transaction=Transaction.builder()
				.transactionType(TransactionType.RETURN)
				.uniqueTransactinID(UUID.randomUUID().toString())
				.book(book)
				.student(student)
				.fees(calculateFine(issueTransaction))
				.build();
		
		transactionRepositoryInterf.save(transaction);
		
		book.setStudent(null);
		bookServiceInterf.save(book);
		
		return transaction.getUniqueTransactinID();		
	}
	
	private double calculateFine(Transaction issTransaction)
	{
		long issueBook=issTransaction.getCreatedOn().getTime();
		long returnBook=System.currentTimeMillis();
		
		long diff=returnBook-issueBook;
		long totalDays=TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS); // miliseconds to days convert.
		
		if(totalDays>max_days)
			return (totalDays-max_days)*10.0;   // fine logic
		
		return 0.0;
	}
}
