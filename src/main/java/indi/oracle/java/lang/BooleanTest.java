package indi.oracle.java.lang;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class BooleanTest {
	boolean a;

	@Test
	@Disabled
	void onImplTest() {
		// boolean 数据类型默认值为false
		System.out.println(a);
	}
	
	@Test
	void go() {
	    int i = 0;
	    boolean s = false && ((i = 1) == 0 || true);
	    System.out.println(i);
	}
}
