package indi.oracle.java.annotation;

public class GetTest {
	
	@Get
	public String go2 = null;
	@Get(value = "hhh2")
	public String my2 = null;

	
	@Register(value = "hhh2")
	public String go() {
		String result = "go";
		return result;
	}
	
	@Register
	public String go2() {
		String result = "go2";
		return result;
	}
}
