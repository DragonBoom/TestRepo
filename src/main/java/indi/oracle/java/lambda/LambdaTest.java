package indi.oracle.java.lambda;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class LambdaTest {

	@Test
	void simpleNullHandlerTest() {
		String[] toTest = { "ff", null, "rua" };
		String a2 = "hello";
		System.out.println(Optional.ofNullable(toTest).map(a -> (a[1] == "1")));
	}
}
