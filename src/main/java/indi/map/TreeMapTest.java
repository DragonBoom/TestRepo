package indi.map;

import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {

	@Test
	public void treeMap() {
		TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
		treeMap.put(1, "first");
		String str = treeMap.get(1);
		System.out.println(str);
	}
}
