package com.nh;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SpringBootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}
	/**
	 * spring boot为我们自动配置了RedisTemplate， 而RedisTemplate
	 * 使用的是JdkSerializationRedisSerializer，这个使用二进制形式
	 * 存储数据，为了方便演示，我们将自己配置RedisTemplate并定义Serializer
	 * @param redisConnectionFactory
	 * @return
	 * @throws UnknownHostException
	 */
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RedisTemplate<Object, Object>redisTemplate(RedisConnectionFactory
			redisConnectionFactory) throws UnknownHostException{
		RedisTemplate<Object, Object> template = new RedisTemplate<Object,Object>();
		template.setConnectionFactory(redisConnectionFactory);
		
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new
				Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om  = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		
		//设置value的序列化采用Jackson2JsonRedisSerializer
		template.setValueSerializer(jackson2JsonRedisSerializer);
		//设置key的序列化采用StringRedisSerializer
		template.setKeySerializer(new StringRedisSerializer());
		
		template.afterPropertiesSet();
		return template;
	}
}
