package indi.jvm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InfoTest {

	@Test
	void test() {
		Runtime runtime = Runtime.getRuntime();
		long maxMem = runtime.maxMemory();
		long freeMem = runtime.freeMemory();
		System.out.println("max mem : " + maxMem);
		System.out.println("free mem : " + freeMem);
		Runnable r = () -> {
			System.exit(0);
		};
		Thread t = new Thread(r);
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("hello?");
	}

}
