package com.example.LibraryManagementSystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.models.MyUser;
import com.example.LibraryManagementSystem.repository.MyUserRepositoryInterf;
import com.example.LibraryManagementSystem.repositoryImpl.MyUserCacheRepositoryImpl;
import com.example.LibraryManagementSystem.request.UserCreateRequest;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MyUserCacheRepositoryImpl myUserCacheRepositoryImpl;
	
	@Autowired
	MyUserRepositoryInterf myUserRepositoryInterf;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${users.authority.student}")
	String studentAuthority;
	
	@Value("${users.authority.admin}")
	String adminAuthority;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		MyUser myUser=myUserCacheRepositoryImpl.get(username);		
		if(myUser==null)
		{
			myUser=myUserRepositoryInterf.findByUsername(username);
			if(myUser!=null)
				myUserCacheRepositoryImpl.set(myUser);
		}
		
		return myUser;
	}
	
	public MyUser userCreate(UserCreateRequest userCreateRequest)
	{
		MyUser.MyUserBuilder myUserBuilder  = MyUser.builder()
				.username(userCreateRequest.getUsername())
				.password(passwordEncoder.encode(userCreateRequest.getPassword()));
		
		if(userCreateRequest.getStudent() !=null) {
			myUserBuilder.authority(studentAuthority);
		}else {
			myUserBuilder.authority(adminAuthority);
		}
		
		return myUserRepositoryInterf.save(myUserBuilder.build());	
	}

}
