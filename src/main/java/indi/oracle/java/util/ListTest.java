package indi.oracle.java.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import indi.junit.extension.TimingExtension;

@ExtendWith(TimingExtension.class)
class ListTest {
	static final Logger logger = LoggerFactory.getLogger("ListTestLogger");

	@Test
	@DisplayName("rua!")
	void arrayListTest() {
		// ArrayList 扩容
		int oldCapacity = 5;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		logger.info("old capacity: {} -> new capacity: {}", oldCapacity, newCapacity);
		// 
		ArrayList<String> arrayList = new ArrayList<String>();
	}

	@Test
	void linkedListTest() {
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add("hello");
	}
	
	@Test
	void queueTest() {
		Queue<String> q = new LinkedList<String>();
		q.add("1");
		q.add("2");
		q.add("3");
		while (!q.isEmpty()) {
			logger.info("queue: {}", q.poll());
		}
	}
}