package indi.oracle.java.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

@ExtendWith(TestSeparateExtension.class)
public class BlockingQueueTest {
	private final static Logger logger = LoggerFactory.getLogger("");

	@Test
	@Disabled
	public void blockingQueueTest() {
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(3);
		bq.add("a");
		bq.add("a1");
		bq.add("a13");
		logger.info("{}", bq);
		bq.add("a134");
		logger.info("{}", bq);
	}
	
	@Test
	@Disabled
	public void linkedQueueTest() {
		LinkedBlockingQueue<String> bq = new LinkedBlockingQueue<String>(3);
		bq.add("a");
		bq.add("a1");
		bq.add("a13");
		logger.info("{}", bq);
		bq.add("a134");
		logger.info("{}", bq);
	}
	
	@Test
	@SneakyThrows
	@Disabled
	void synchronousQueueTest() {
	    SynchronousQueue<String> queue = new SynchronousQueue<>();
	    System.out.println("开始添加元素");
	    // 开启新线程以获取元素（若注释掉这块将添加失败）
	    Thread t = new Thread(() -> {
	        String str = queue.poll();
	        System.out.println("获取元素成功: " + str);
	    });
	    t.start();
	    // 必须有线程等待获取元素，才能添加元素
	    boolean success = queue.offer("hello", 5, TimeUnit.SECONDS);
	    if (success) {
	        System.out.println("添加元素成功");
	    } else {
	        System.out.println("添加元素失败");
	    }
	}
	
	@Test
	@SneakyThrows
	void delayQueueTest() {
	    DelayQueue<Delayed> queue = new DelayQueue<>();
	}
}
