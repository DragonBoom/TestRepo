package indi.oracle.java.thread;

import org.junit.jupiter.api.Test;

public class AliveTest {

	@Test
	void go() {

		Thread t = new Thread(() -> {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Integer i = null;
			System.out.println(i.intValue());
		});
		t.start();

		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println(t);
				System.out.println(t.isAlive());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
