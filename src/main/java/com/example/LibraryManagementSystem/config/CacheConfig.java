package com.example.LibraryManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheConfig {
	
	@Bean
	public LettuceConnectionFactory getConnectionFactory() {
		
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
				"redis-10544.c264.ap-south-1-1.ec2.cloud.redislabs.com",10544);
		
		redisStandaloneConfiguration.setPassword("rAWvy2yldaIx4RxYHib2rkWHACGh5Cbd");
		
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(
				redisStandaloneConfiguration);
		
		return lettuceConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> getTemplate(){
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		
		redisTemplate.setConnectionFactory(getConnectionFactory());

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		
		return redisTemplate;
	}
}
