package indi.oracle.java.jvm;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OOMTest {
	
	/**
	 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 * 			堆最小值	堆最大值	当内存溢出时Dump出内存堆转储快照
	 * 结果：
	 * java.lang.OutOfMemoryError: Java heap space
	 * Dumping heap to java_pid360008.hprof ...
	 * Heap dump file created [22197345 bytes in 0.097 secs]
	 * @param args
	 */
//	@Test
	public static void main(String[] args) {
	    try {
	        List<String> list = new ArrayList<>();
	        int i = 9999;
	        while (i > 0) {
	            list.add("1 " + System.currentTimeMillis());
	            i--;
	        }
	        
	        System.out.println(list);
	    } catch (Throwable e) {
	        e.printStackTrace();
	    }
	}
	
	static class OOMObject {
		private byte[] bytes = new byte[1234]; 
	}
}
