package indi.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

public class SetTest {

	@Test
	public void treeSetTest() {
		TreeSet<MyClass> hashSet = new TreeSet<MyClass>();
		MyClass m1 = new MyClass(1);
		MyClass m2 = new MyClass(2);
		MyClass m3 = new MyClass(3);
		hashSet.add(m2);
		hashSet.add(m3);
		hashSet.add(m1);
		Iterator<MyClass> i = hashSet.iterator();
		while (i.hasNext()) {
			i.next().show();
		}
	}

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
}
