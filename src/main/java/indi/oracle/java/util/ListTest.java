package indi.oracle.java.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import indi.junit.extension.TimingExtension;

@ExtendWith(TimingExtension.class)
class ListTest {
	static final Logger logger = LoggerFactory.getLogger("ListTestLogger");
	
	List<Integer> intList = new LinkedList<>();
	
	@BeforeEach// must void, not static, not private
    void init() {
        intList.add(1);
	    intList.add(2);
	    intList.add(3);
	    intList.add(4);
	    intList.add(5);
	    intList.add(6);
	    intList.add(9);
	    intList.add(15);
	    intList.add(50);
	    intList.add(58);
	}

	@Test
	@Disabled
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
	@Disabled
	void linkedListTest() {
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add("hello");
	}
	
	@Test
	@Disabled
	void queueTest() {
		Queue<String> q = new LinkedList<String>();
		q.add("1");
		q.add("2");
		q.add("3");
		while (!q.isEmpty()) {
			logger.info("queue: {}", q.poll());
		}
	}
	
	@Test
	void iteratorTest() {
	    System.out.println(intList);
	    // remove 1
	    Iterator<Integer> i1 = intList.iterator();
	    while (i1.hasNext()) {
	        Integer i = i1.next();
	        if (i % 2 == 0) {
	            i1.remove();
	        }
	    }
	    System.out.println(intList);
	    // remove 2
	    Iterator<Integer> i2 = intList.iterator();
	    while (i2.hasNext()) {
	        Integer i = i2.next();
	        if (i % 3 == 0) {
	            i2.remove();
	        }
	    }
	    System.out.println(intList);
	    
	}
}