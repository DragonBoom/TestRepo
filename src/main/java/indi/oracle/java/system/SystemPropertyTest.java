package indi.oracle.java.system;

import org.junit.jupiter.api.Test;

public class SystemPropertyTest {

	@Test
	void main() {
		// 获取系统属性
		String property = System.getProperty("java.vendor");
		System.out.println(property);
		// 仅对当前Java 程序生效
		System.clearProperty("java.vendor");
	}
}
