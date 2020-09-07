package indi.oracle.java.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class BooleanTest {
    Boolean nullBoolean = null;
	boolean a;

	@Test
	@Disabled
	void onImplTest() {
		// boolean 数据类型默认值为false
		System.out.println(a);
	}
	
	/**
	 * 
	 */
	@Test
	@Disabled
	void nullWhenTernaryOperatorTest() {
	    String s = nullBoolean ? "t" : "f";// case java.lang.NullPointerException
	    System.out.println(s);
	}
	
	@Test
	void wrapperClassTest() {
	    boolean result = nullBoolean == Boolean.TRUE;
	    boolean result2 = true == Boolean.TRUE;
	    boolean result3 = false == Boolean.TRUE;

	    Assertions.assertFalse(result);
	    Assertions.assertTrue(result2);
	    Assertions.assertFalse(result3);
	}
	
	@Test
	void operatorTest() {
	    int i = 0;
	    // 即不会执行下一行中的i++
	    System.out.println(i > 0 && ++i > 0);// print: false
	    System.out.println(i);// print: 0
	    
	}
}
