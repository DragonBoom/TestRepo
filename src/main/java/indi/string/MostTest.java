package indi.string;

import org.junit.Test;

public class MostTest {
	// StringBuffer 与 StringBuilder 都是继承自AbstractStringBuilder
	// 没有同步锁版本的StringBuffer
	StringBuilder strBuilder = new StringBuilder();
	// 通过在方法上加同步锁实现线程安全
	StringBuffer strBuffer = new StringBuffer();
	
	@Test
	public void test1() {
		
	}
}
