package indi.oracle.java.util;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hashMap 中的modcount 是用来记录修改次数，用于iterator操作时的fail-fast异常的判断
 * <p>
 * ps.java方法的默认修饰符是default(只在接口中可写出)，与protected唯一不同是只能被同一包中的子类访问
 * <p>
 * HashMap 中 newTab[e.hash & (newCap - 1)] = e;
 * <p>
 * 扰动函数: HashMap 的节点(node)求hash值的函数(h = key.hashCode()) ^ (h >>> 16)称为 扰动函数 ,
 * int 最大为32位，h>>>16是將原始哈希值的高半区移到低办区,得到0000****的数值，
 * 该函数其实是將哈希值(通过objects的方法获取)的高位区与低位区在低位区进行异或运算，即在低位区得到混淆了原始哈希码的高、低位的序列,
 * 变相在低位区保存了高位区的信息(高位区保持不变), 方便之后的 hash & (length - 1) 在低位区取值能取到分布更均匀的数
 * <p>
 * HashMap 的数组长度为2的n次幂，是因为HashMap的hash算法，是通过key的hashCode & 数组长度-1
 * 来获得该key的node在node数组上的位置， 若数组长度为2的n次幂时，2^n -
 * 1的2进制形式是0000...与n个1，此时进行与哈希值进行&运算，即將其截取了小于等于n位的值，从而降低了数组长度的要求,
 * 并且低位全是1，也使得结果更加均匀
 * <p>
 * 
 * @author 13124
 */
public class HashMapTest {
	private static final Logger logger = LoggerFactory.getLogger(HashMapTest.class);
	private static HashMap<Integer, String> hashMap = new HashMap<Integer, String>(123);

	@Before
	public void before() {
		hashMap.put(1, "ff");
		hashMap.remove(2);
		hashMap.remove(1);
	}

	@Test
	public void nullTest() {
		// HashMap中 null 的hash值取0
		hashMap.put(null, "null");
		logger.info("{}", hashMap.get(null));
		logger.info("{}", "" == (String) hashMap.get("qq"));
	}

	@Test
	public void multiThread() {
	}
}
