package indi.oracle.java.jvm;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OOMTest {
	
	/**
	 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 * 			堆最小值	堆最大值	当内存溢出时Dump出内存堆转储快照
	 * 结果：
	 * <pre>
	 * java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid29652.hprof ...
Heap dump file created [6017299 bytes in 0.055 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    at indi.oracle.java.jvm.OOMTest.main(OOMTest.java:25)
    </pre>
	 * @param args
	 */
//	@Test
	public static void main(String[] args) {
	    try {
	        List<Object> list = new ArrayList<>();
	        int i = 9999;
	        while (i > 0) {
	            list.add(new OOMObject());
	            i--;
	        }
	        
	    } catch (Throwable e) {
	        e.printStackTrace();
	    }
	    System.out.println(Runtime.getRuntime().maxMemory()/ 1024 / 1024);
	}
	
	private static class OOMObject {
		private byte[] bytes = new byte[1234]; 
	}
}
