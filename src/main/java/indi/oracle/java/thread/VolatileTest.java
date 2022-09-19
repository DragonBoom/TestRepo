package indi.oracle.java.thread;

import org.junit.jupiter.api.Test;

class VolatileTest {

	/**
	 * volatile修饰符的效果? <br>
	 * Java多线程下基本数据类型的更新不一定是同步的！！
	 * 对常量使用volatile修饰符能否解决该问题？答案是可以，每次访问volatile修饰符修饰的常量都是其最新的值（可见性）
	 * 
	 * <p>其中的原理，应该是执行了一段时间死循环的线程，将从CPU缓存中取值，而不是内存，所以无法获取到值的变化；用volatile
	 * 可以直接向内存取值，所以就不会有问题
	 */
	@Test
	void volatileForConstant() {
		Door d = new Door();
		Thread t1 = new Thread(d);
		Thread t2 = new Thread(d);
		t1.start();
		System.out.println(t1);
		// d.unlock(); // 若不让t1执行一段时间的循环，不会无法离开死循环
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		d.unlock();
		t2.start();// t2能正常解锁
		
		System.out.println(">>unlock complete<<");
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("progress over");

	}

	private class Door implements Runnable {
		// 当lock不用volatile修饰符时线程会死循环！
		private Boolean lock = true;
//		private volatile Boolean lock = true;
		private int i = 0;

		@Override
		public void run() {
			while (lock) {
				i++;
			}
			// 若lock没有用volatile修饰，线程将无法执行到这一步
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
