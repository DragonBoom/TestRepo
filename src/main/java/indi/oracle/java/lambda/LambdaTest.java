package indi.oracle.java.lambda;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class LambdaTest {

	@Test
	void simpleNullHandlerTest() {
		String[] toTest = { "ff", null, "rua" };

		System.out.println(Optional.ofNullable(toTest).map(a -> (a[1] == "1")));// print: Optional[false]
		
		System.out.println(null == "1");// print: false
		// null == 1 编译不通过
		System.out.println((Integer)null == 1);// case: NullPointerException
	}
}
