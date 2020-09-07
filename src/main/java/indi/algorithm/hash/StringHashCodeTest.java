package indi.algorithm.hash;

public class StringHashCodeTest {

	public static void main(String[] args) {
		// string 的 hashCode 方法并不是万无一失，两个不同的字符串的hashCode方法可能拥有相同的结果！如下：
		String s = new String("gdejicbegh");
		String s2 = new String(" hgebcijedg");
		System.out.println(s2.hashCode());
		System.out.println(s.hashCode());
	}
}
