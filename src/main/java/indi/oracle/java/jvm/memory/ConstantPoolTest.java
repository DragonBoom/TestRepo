package indi.oracle.java.jvm.memory;

import org.junit.Test;

// Java 编程语言只有值传递参数。当一个对象实例作为一个参数被传递到方法中时，参数的值就是该对象的引用一个副本。指向同一个对象,对象的内容可以在被调用的方法中改变，
// 但对象的引用(不是引用的副本)是永远不会改变的。 
public class ConstantPoolTest {

	@Test
	public void mainTest() {
		String s = "hell";
		String s2 = "o";
		String s3 = "hello";
		String s4 = s + s2;
		// 字符串池中不存在s4
		if (s4 == s3) {
			System.out.println("s4 == s3");
		} else {
			System.out.println("s4 != s3");
		}
		// 执行intern()方法后，將s4加入到字符串池
		s4 = s4.intern();
		System.out.println("after intern():");
		if (s4 == s3) {
			System.out.println("s4 == s3");
		} else {
			System.out.println("s4 != s3");
		}
		
	}

	@Test
	public void stringTest() {
		String a = "a";
		String b = "b";
		String b2 = b;
		String a3 = new String("a");
		String b3 = new String("b");
		changeString(a3, b3);
		System.out.println(a3 + "---" + b3);
		if (a3 == a) {
			System.out.println("a3 equal a");
		} else {
			System.out.println("a3 not equal a");
		}
		if (b3 == b2) {
			System.out.println("b3 equal b2");
		} else {
			System.out.println("b3 not equal b2");
		}
		changeString(a, b2);
		System.out.println(a + "---" + b2);

		b = a;
		a = a + b;
		System.out.println(a + "---" + b);

		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = sb;
		changeStringBuffer(sb);
		if (sb == sb2) {
			System.out.println("equal");
		}
		System.out.println(sb.toString());
	}

	private void changeString(String a, String b) {
		String temp = b;
		b = a;
		a = temp;
	}

	private void changeStringBuffer(StringBuffer a) {
		a = a.append("change complete");
	}
	
	@Test
	public void integerTest() {
		// Integer 默认缓存-128~127的数
		Integer i1 = Integer.valueOf(150);
		Integer i2 = Integer.valueOf(150);
		if (i1 != i2) {
			System.out.println("for Integer '" + i1 + "' != '" + i2 + "'");
		} else {
			System.out.println("for Integer '" + i1 + "' == '" + i2 + "'");
		}
		Integer i3 = 99;
		Integer i4 = 99;
		if (i3 != i4) {
			System.out.println("for Integer '" + i3 + "' != '" + i4 + "'");
		} else {
			System.out.println("for Integer '" + i3 + "' == '" + i4 + "'");
		}
		
		int i5 = Integer.valueOf(250);
		int i6 = Integer.valueOf(250);
		if (i5 != i6) {
			System.out.println("for int '" + i5 + "' != '" + i6 + "'");
		} else {
			System.out.println("for int '" + i5 + "' == '" + i6 + "'");
		}
		int i7 = 199;
		Integer i8 = 199;
		Integer i9 = 199; 
		if (i7 != i9) {
			System.out.println("i7 not equal i9");
		} else {
			System.out.println("i7 equal i9");
		}
		if (i7 != i8) {
			System.out.println("i7 not equal i8");
		} else {
			System.out.println("i7 equal i8");
		}
		if (i8 != i9) {
			System.out.println("i8 not equal i9");
		} else {
			System.out.println("i8 equal i9");
		}
	}

}
