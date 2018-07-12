package indi.jvm;

import java.util.ArrayList;
import java.util.List;

class OOMTest {
	
	/**
	 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 * 			堆最小值	堆最大值	当内存溢出时Dump出内存堆转储快照
	 * 结果：
	 * java.lang.OutOfMemoryError: Java heap space
	 * Dumping heap to java_pid360008.hprof ...
	 * Heap dump file created [22197345 bytes in 0.097 secs]
	 * @param args
	 */
	// @Test
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		int i = 99;
		while (i > 0) {
			list.add(new OOMObject());
			i--;
		}
	}
	
	static class OOMObject {
		
	}
}
