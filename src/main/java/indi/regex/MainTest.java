package indi.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;


public class MainTest {

//	@Test
	public void regexTest() {
		// 使用正则时注意有时需要转义
		Pattern p = Pattern.compile(".*?(?=\\()");
		String toMatch = "北京市(海淀区)(朝阳区)（西城区）";
		Matcher m = p.matcher(toMatch);
		if (m.find())
			System.out.println(m.group());
	}
	
	@Test
	void test2() {
	    Pattern p = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
	    boolean r = p.matcher("fff").find();
	    System.out.println(r);
	}
}
