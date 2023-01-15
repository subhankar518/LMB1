package com.example.LibraryManagementSystem.repositoryImpl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagementSystem.models.MyUser;

@Repository
public class MyUserCacheRepositoryImpl {
	
	private final String USER_KEY_PREFIX="user::";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	public void set(MyUser myUser)
	{
		String key=getKey(myUser.getUsername());
		redisTemplate.opsForValue().set(key,myUser,24,TimeUnit.HOURS); // key, value, expire time, time unit
	}
	
	public MyUser get(String username)
	{
		String key=getKey(username);
		return (MyUser)redisTemplate.opsForValue().get(key);
		
	}
	
	private String getKey(String username)
	{
		return USER_KEY_PREFIX+username;
	}
}
