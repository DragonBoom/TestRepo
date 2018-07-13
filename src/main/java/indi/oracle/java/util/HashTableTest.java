package indi.oracle.java.util;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

/**
 * Hashtable 通过直接在方法上加synchronized 修饰符，即通过锁定所在对象实现线程安全
 * @author 13124
 *
 */
public class HashTableTest {

	@Test
	public void hashTable() {
		Map<Integer, String> hashTable = new Hashtable<Integer, String>();
		// Hashtable 的键与值都不能为null， HashMap 可以
		// Hashtable 的hash值直接使用Object类的hashCode 方法，不对null做特殊处理,因此key为null时会报错
		/**
		 * Hashtable 的value值不能为null，应该是因为当value可能为null时，
		 * 无法只用Hashtable提供的方法实现线程安全的获取并根据结果判断是否存在的操作<br>
		 * 而HashMap 因为本身就是线程不安全的，不会用于多线程环境，可通过在get之前通过诸如contains,
		 * containsKey进行判断， 所以支持vaule为null
		 */
		hashTable.put(null, "ff");
		hashTable.get(null);
	}
}
