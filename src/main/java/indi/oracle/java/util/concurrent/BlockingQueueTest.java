package indi.oracle.java.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockingQueueTest {
	private final static Logger logger = LoggerFactory.getLogger("");

	@Test
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
	public void linkedQueueTest() {
		LinkedBlockingQueue<String> bq = new LinkedBlockingQueue<String>(3);
		bq.add("a");
		bq.add("a1");
		bq.add("a13");
		logger.info("{}", bq);
		bq.add("a134");
		logger.info("{}", bq);
	}
}
