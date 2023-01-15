package com.example.LibraryManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.LibraryManagementSystem.models.Admin;
import com.example.LibraryManagementSystem.models.MyUser;
import com.example.LibraryManagementSystem.repository.AdminRepositoryInterf;
import com.example.LibraryManagementSystem.repository.MyUserRepositoryInterf;

@SpringBootApplication
public class LibraryManagementSystemApplication implements CommandLineRunner{
	
	@Autowired
	MyUserRepositoryInterf myUserRepositoryInterf;
	
	@Autowired
	AdminRepositoryInterf adminRepositoryInterf;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${users.authority.student}")
	String studentAuthority;
	
	@Value("${users.authority.admin}")
	String adminAuthority;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		MyUser myUser=MyUser.builder()
				.username("saikat")
				.password(passwordEncoder.encode("saikat123"))
				.authority(adminAuthority)
				.build();
		myUser=myUserRepositoryInterf.save(myUser);
		
		Admin admin=Admin.builder()
				.name("Saikat Bose")
				.email("saikat@gmail.com")
				.myUser(myUser)
				.build();
		adminRepositoryInterf.save(admin);				
		
	}

}
