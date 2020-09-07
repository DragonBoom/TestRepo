package indi.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

class JedisTest {

	// @Test
	void first() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		Jedis j = pool.getResource();
		j.set("fff", "www");
		System.out.println(j.get("fff"));
		pool.close();
	}
}
