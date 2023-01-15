package com.example.LibraryManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Value("${users.authority.student}")
	String studentAuthority;
	
	@Value("${users.authority.admin}")
	String adminAuthority;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.httpBasic().and().authorizeHttpRequests()
		.antMatchers("/student/**").hasAuthority(studentAuthority)
		.antMatchers("/createStudent/**").permitAll()
		.antMatchers("/studentForAdmin/**").hasAuthority(adminAuthority)
		.antMatchers("/searchBook/**").hasAnyAuthority(studentAuthority,adminAuthority)
		.antMatchers("/saveBook/**").hasAuthority(adminAuthority)
		.antMatchers("/home").permitAll()
		.and().formLogin();
	}
	
}
