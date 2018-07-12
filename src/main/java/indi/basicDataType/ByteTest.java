package indi.basicDataType;

import org.junit.Test;

public class ByteTest {

	// byte是java的基本数据类型之一，每个byte存储着一个字节的数据
	// 一个字符(char)用两个字节来表示，默认unicode 编码，可表示几乎所有文字
	// char String 是编译后(编译为unicode)的byte，而byte是以2进制补码形式保存的未编译的数据(short, int, long都是)
	@Test
	public void wByte() {
		String str = new String("hello");
		System.out.println("---for " + str + "---");
		byte[] bytes = str.getBytes();
		String[] binary = new String[str.length()];
		int i = 0;
		System.out.println("---print byte array---");
		for (byte b : bytes) {
			System.out.println(b);
		}
		System.out.println("---byte 2 binary---");
		while (i < str.length()) {
			// 將字节数组的每一个元素转换为2进制的结果：
			System.out.println(binary[i] = Integer.toBinaryString((int) bytes[i]));
			i++;
		}
		System.out.println("---binary 2 char---");
		i = 0;
		while (i < str.length()) {
			System.out.println((char) Integer.parseInt(binary[i], 2));
			i++;
		}
		// byte的范围为-2^7~2^7-1，將int类型转换为byte时，当int的值超过byte的最大或最小值时,会导致乱码
		byte b2 = (byte) (Byte.MIN_VALUE - 257);
		// Byte的toString 方法是將byte转换为int，再將int转换为String
		System.out.println(b2);
		System.out.println((char) (b2));
	}
}
