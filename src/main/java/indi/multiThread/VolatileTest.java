package indi.multiThread;

import org.junit.jupiter.api.Test;

class VolatileTest {

	/**
	 * volatile修饰符的效果? <br>
	 * Java多线程下基本数据类型的更新不一定是同步的！！
	 * 对常量使用volatile修饰符能否解决该问题？答案是可以，每次访问volatile修饰符修饰的常量都是其最新的值
	 */
	@Test
	void volatileForConstant() {
		Door d = new Door();
		Thread t1 = new Thread(d);
		Thread t2 = new Thread(d);
		t1.start();
		t2.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		d.unlock();
		
		System.out.println(">>unlock complete<<");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private class Door implements Runnable {
		// 当lock不用volatile修饰符时线程会死循环！
		private volatile Boolean lock = true;
		private int i = 0;

		@Override
		public void run() {
			while (lock) {
				i++;
			}
			System.out.println("---Door opening!---" + i);
		}

		public void unlock() {
			lock = false;
		}

		public boolean isLock() {
			return lock;
		}
	}
}
