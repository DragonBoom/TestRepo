package indi.oracle.java.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.output.TeeOutputStream;
import org.junit.jupiter.api.Test;

public class OutputStreamTest {

	// @Test
	void bufferedOutputSteamTest() {
		BufferedOutputStream outputStream = null;
		try {
			outputStream = new BufferedOutputStream(new FileOutputStream(new File("ff")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] bytes = new String("Are you OK?").getBytes();
 		try {
 			// 实际上底层仍然是逐字节写入输出流...
			outputStream.write(bytes);
			// 执行完这一步才会将字节数组写入... 
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 利用PrintStream 更方便的输出字符串
	// 利用apache common io 的 TeeOutputStream 实现同时写入字节数组到两个输出流
	@Test
	void teeOutputStreamTest() {
		TeeOutputStream teeStream = null;
		try {
			teeStream = new TeeOutputStream(System.out, new FileOutputStream("ff"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintStream ps = new PrintStream(teeStream);
		ps.println("waaw");
	}
}
