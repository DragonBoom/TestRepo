package indi.oracle.java;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import indi.util.TestObjects;
import indi.util.TestObjects.Plain1ArgObj;
import indi.util.TestUtils;

/**
 * 测试java基础语法
 * 
 * @author wzh
 * @since 2020.09.29
 */
@ExtendWith(TestSeparateExtension.class)
class GrammarTest {
	int i = 0;

	static {
		System.out.println("init!!");
	}

	{
		System.out.println("init 2 !!");
	}

	static {
		System.out.println("init 3 !!");
	}

	// @Test
	public void testOnly() {
		int all = 0;
		int n = 9;
		int t1 = 0;
		int t2 = 0;
		int t3 = 0;
		for (int i = 0; i <= n; i++) {
			all++;
			t1++;
			System.out.println("-" + i);
			for (int j = 0; j <= i; j++) {
				all++;
				t2++;
				System.out.println("--" + j);
				for (int k = 0; k <= j; k++) {
					all++;
					t3++;
					System.out.println("---" + k);
				}
			}
		}
		System.out.println(all);
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
	}

	// @Test
	public void testReturn() {
		try {
			System.out.println("try part");
			if (true)
				throw new Error("print error right?");
		} catch (Error e) {
			e.printStackTrace();
		} finally {
			System.out.println("finally part");
		}
	}

	// @Test
	public void testStaticInternalClass() {
		Only o = new Only();
		Only o2 = new Only();
		int i1 = o.getInt();
		int i2 = o2.getInt();
		System.out.println(o.getClass() + " ! " + i1 + "  " + i2);
	}

	private static class Only {
		private int i = 0;

		public Only() {
			i++;
		}

		public int getInt() {
			return i;
		}
	}

	// @Test
	void arrayLengthTest() {
		int[] is = new int[0];
		System.out.println(is.length);
	}

	// @Test
	void lengthTest() {
		int len = 1 << 23;
		System.out.println(len);
		System.out.println(countBit(len));
		System.out.println(Integer.MAX_VALUE);
		int after = cut(len >> 8, 8);
		int after2 = cut(len >> 16, 8);
		System.out.println(cut(len, 8));
		System.out.println(after);
		System.out.println(after2);
		System.out.println((after2 << 16) + (after << 8) + cut(len, 8));
	}

	// 对正整数截取前8位(bit)(一个byte占8bit)的内容，其他忽略不计
	private int cut(int toCut, int limit) {
		int i = 0;
		int temp = 0;
		int result = 0;
		while (i < limit && toCut > temp) {
			temp = 1 << i;
			if ((toCut & temp) != 0) {
				result += temp;
			}
			i++;
		}
		return result;
	}

	private int countBit(int toCount) {
		int i = 1;
		int temp = 1;
		while (toCount >= temp) {
			i++;
			temp = 1 << i;
		}
		return i - 1;
	}

	public void staticInnerClassTest() {
		Inner i = new Inner();
		Inner i2 = new Inner();
		Assertions.assertEquals(i.i, i2.i);
	}

	private static class Inner {
		// i不是静态的，即静态内部类的值域不是静态的!!
		int i = 0;

		Inner() {
			i++;
		}
	}

	// 静态初始化块只会执行一次，非静态初始化块每次创建对象时都会执行一次
	// @Test
	void initCodeTest() {
		String a = GrammarTest.class.getName();
		GrammarTest o = new GrammarTest();
		String b = GrammarTest.class.getName();
	}
	
	// 三元运算符测试
	@Test
	void ternaryTest() {
	    Plain1ArgObj obj = new TestObjects.Plain1ArgObj();
	    @SuppressWarnings("unused")
        boolean test = false ? TestUtils.doAndReturn(() -> obj.setArg1("f"), true) : false;
	    Assertions.assertNull(obj.getArg1());// 预测：执行三元运算时，只执行匹配的一元
	}
}
