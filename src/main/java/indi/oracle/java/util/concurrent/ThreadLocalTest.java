package indi.oracle.java.util.concurrent;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

	class A {
		private int i = 0;

		void set(int i) {
			this.i = i;
		}

		int get() {
			return i;
		}
	}

	@Test
	void go() {
		A a = new A();
		for (int i = 0; i < 10; i++) {
			Runnable r = () -> {
				ThreadLocal<A> local = new ThreadLocal<A>() {
					@Override
					protected A initialValue() {
						return new A();
					}
				};
				System.out.println(local.get());
				local.get().set(55);
				local.set(a);
				System.out.println(local.get());
			};
			new Thread(r).start();
		}
		while (Thread.activeCount() > 2) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
