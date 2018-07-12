package indi.collection;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

public class TreeMapTest {

	private class MyClass implements Comparable<MyClass> {
		private int order;

		MyClass(int order) {
			this.order = order;
		}

		public void show() {
			System.out.println("order: " + order);
		}

		@Override
		public int compareTo(MyClass o) {
			return order - o.order;
		}
	}

	@Test
	void go() {
		TreeMap<MyClass, String> t = new TreeMap<>(Collections.reverseOrder());
		MyClass m1 = new MyClass(1);
		MyClass m2 = new MyClass(2);
		MyClass m3 = new MyClass(3);
		t.put(m1, "m1");
		t.put(m3, "m3");
		t.put(m2, "m2");
		for (Entry<MyClass, String> e : t.entrySet()) {
			e.getKey().show();
		}
	}
}
