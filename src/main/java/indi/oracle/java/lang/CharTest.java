package indi.oracle.java.lang;

import org.junit.Test;

public class CharTest {
	
	// java 默认使用unicode编码，支持几乎所有文字，char类型默认值为空格(0)
	@Test
	public void arrayTest() {
		char[] chars = new char[5];
		for (char c : chars) {
			System.out.print("->  ");
			System.out.println((int) c);
		}
		char c = '中';
		System.out.println((int) c);
	}
	
	// 字节(byte)是计算机中存储数据的单位，而字符(char)是文字或符号
	// 同时byte 与 char 也是java中的一种基本数据类型
	@Test
	public void wChar() {
		System.out.println("the number of byte to represent a char: " + Character.BYTES);
	}

}
