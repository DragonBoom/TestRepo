package indi.oracle.java.util.concurrent;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;

/**
 * ConcurrentHashMap: 升级版的HashTable
 * 
 * @author 13124
 *
 */
public class ConcurrentHashMapTest {
	@SuppressWarnings("unused")
	private static int i = 0;
  
	/**
	 * ConcurrentHashMap 与 HashTable 不一样，不是在方法上添加synchronized修饰符从而直接將锁定监视器设为类本身，
	 * 而是仅仅锁定需要访问的Entry节点，即访问不同结点的操作不用竞争锁
	 */
	@Test
	void concurrentTest() {
		int i = 1;
		int j = 2;
		if (i == j) {
			System.out.println("1");
		} else if (i == 1) {
			System.out.println(--i);
			System.out.println(i--);
		} else if (j == 1) {
			System.out.println("3");
		} else {
			System.out.println("4");
		}
		
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("f", "e");
		map.put("f2", "e");
		map.clear();
	}
}
