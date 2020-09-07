package indi.oracle.java.jvm;

import org.junit.jupiter.api.Test;

class RuntimeInfoTest {

    /**
     * 测试通过Runtime类获取信息
     * 
     * @author DragonBoom
     * @since 2020.06.18
     */
	@Test
	void test() {
		Runtime runtime = Runtime.getRuntime();
		long maxMem = runtime.maxMemory();
		long freeMem = runtime.freeMemory();
		System.out.println("max mem : " + maxMem);
		System.out.println("free mem : " + freeMem);
		Runnable r = () -> {
			System.exit(0);// close application
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
