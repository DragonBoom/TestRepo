package indi.redis;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

class LettuceTest {
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 6379;
	private final static String PASSOWRD = "!qQ1312449403";
	
	private static RedisCommands<String, String> stringCommands = null;
	private static RedisCommands<String, String> stringCommands2 = null;

	static {
	}
	
	@BeforeAll
	static void init() {
	    // 通过建造模式创建连接，可设置密码
	    RedisURI uri = RedisURI.builder().withHost(HOST).withPort(PORT).build();
	    RedisClient client = RedisClient.create(uri);
	    // 使用特定解码/编码器创建新连接
	    StatefulRedisConnection<String, String> connection2 = client.connect(new StringCodec());
	    // 创建同步的操作类，不会创建新连接
	    RedisCommands<String, String> sync2 = connection2.sync();
	    
	    // 使用特定解码/编码器创建新连接
	    StatefulRedisConnection<String, String> connection = client.connect(new StringCodec());
	    // 创建同步的操作类，不会创建新连接
	    RedisCommands<String, String> sync = connection.sync();
	    
	    stringCommands = sync;
	    stringCommands2 = sync2;
//		RedisPubSubCommands<String, String> connection = client.connectPubSub().sync();
	    
	}
	
	/**
	 * 测试brpop阻塞的影响。
	 * 
	 * <p>经测试，brpop的阻塞，只影响同一连接！
	 * 
	 * @author DragonBoom
	 * @since 2020.06.26
	 */
	@Test
	void blockTest() {
	    System.out.println(stringCommands);
	    String key = "LettuceTestList";
	    // 清空队列
	    System.out.println("del key: " + stringCommands.del(key));;
	    // 以下函数使用与brpop相同的连接修改同一个队列
	    Runnable function1 = () -> {
	        // 2s后执行
	        long deferral = 2;
	        try {
                TimeUnit.SECONDS.sleep(deferral);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	        // 测试在阻塞后，该连接能否用于往队列插入值
	        System.out.println("start lpush");
	        // 【测试发现】必须在brpop阻塞超时后，才会执行下面的代码
	        // 并且不同键也会阻塞
	        System.out.println("lpush after " + deferral + " s: test" + stringCommands.lpush(key, "test"));
	    };
	    // 以下函数使用与brpop不同的连接修改同一个队列
	    Runnable function2 = () -> {
	        // 2s后执行
	        long deferral = 2;
	        try {
	            TimeUnit.SECONDS.sleep(deferral);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        // 测试在阻塞后，该连接能否用于往队列插入值
	        System.out.println("start connection2 lpush");
	        // 【测试发现】不必等brpop阻塞，能直接执行下面的代码
	        System.out.println("connection2 lpush after " + deferral + " s: test" + stringCommands2.lpush(key, "test"));
	    };
	    
	    new Thread(function1).start();
//	    new Thread(function2).start();
	    
	    System.out.println("start brpop");
	    System.out.println("brpop: " + stringCommands.brpop(10, key));
	}

}
