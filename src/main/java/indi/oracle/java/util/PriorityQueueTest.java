package indi.oracle.java.util;

import java.util.Collections;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriorityQueueTest {
	private final static Logger logger = LoggerFactory.getLogger("");

	/**
	 * 优先级越大越优先取出
	 */
	@Test
	public void priorityQueueTest() {
		PriorityQueue<MyClass> queue = new PriorityQueue<>(Collections.reverseOrder());
		MyClass m1 = new MyClass(7);
		MyClass m2 = new MyClass(9);
		MyClass m3 = new MyClass(3);
		queue.offer(m1);
		queue.offer(m3);
		queue.offer(m2);
		System.out.println(queue.size());
		m1.order = 1;
		// PriorityQueue 只有在入队时会排序!!
		queue.offer(new MyClass(10));
		// 且有新元素入队也不一定会重新排序!!
		while (queue.size() > 0) {
			queue.poll().show();
		}
	}

	private class MyClass implements Comparable<MyClass> {
		private int order;

		MyClass(int order) {
			this.order = order;
		}

		public void show() {
			logger.info("order: {}", order);
		}

		@Override
		public int compareTo(MyClass o) {
			return order - o.order;
		}
	}
}
