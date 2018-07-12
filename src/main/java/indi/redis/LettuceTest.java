package indi.redis;

import org.junit.jupiter.api.Test;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

class LettuceTest {
	private final static String HOST = "127.00.0.1";
	private final static int PORT = 6379;
	private static RedisCommands<String, String> stringCommands = null;

	static {
		RedisURI uri = RedisURI.builder().withHost(HOST).withPort(PORT).build();
		RedisClient client = RedisClient.create(uri);
		StatefulRedisConnection<String, String> connection2 = client.connect(new StringCodec());
		RedisCommands<String, String> sync2 = connection2.sync();
		stringCommands = sync2;
		RedisPubSubCommands<String, String> connection = client.connectPubSub().sync();
	}

	@Test
	void pubsub() {

	}
}
