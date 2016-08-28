package com.sun.master2slave;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class Main {
	private static RedisTemplate redisTemplate;

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		setRedisTemplate((RedisTemplate) context.getBean("redisTemplate"));
		set();

		// String result = new String(get("age2".getBytes()));
		// System.out.print(result);

	}

	public static void set() {
		getRedisTemplate().execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection) {
				connection.set("age2".getBytes(), "223".getBytes());
				return 1L;
			}
		});
	}

	public static byte[] get(final byte[] key) {
		return (byte[]) getRedisTemplate().execute(new RedisCallback() {
			public byte[] doInRedis(RedisConnection connection) {
				return connection.get(key);
			}
		});
	}

	public static RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public static void setRedisTemplate(RedisTemplate redisTemplate) {
		Main.redisTemplate = redisTemplate;
	}
}
