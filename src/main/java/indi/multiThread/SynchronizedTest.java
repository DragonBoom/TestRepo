package indi.multiThread;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SynchronizedTest {
	private static final Logger logger = LoggerFactory.getLogger("");

	// sleep 会使线程失去对监视器的锁定吗？不会
	// 对当前线程执行interrupt()会能马上中断当前线程吗？不能，对当前线程执行interrupt()仅是不会触发权限异常...
	// @Test
	void synchronizedSleepTest() {
		MyLock lock = new MyLock();
		MyInnerThread m1 = new MyInnerThread(lock, 1000);
		MyInnerThread m2 = new MyInnerThread(lock, 2000);
		
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m2);
		t1.start();
		t2.start();
		while (Thread.activeCount() > 1) {
			try {
				Thread.sleep(1000);			
			} catch (InterruptedException e) {
				logger.info("A interrupted exception occur by sleep()!!");
			}
		}
	}
	
	private class MyLock {
		// nothing
	}

	private class MyInnerThread implements Runnable {
		private int limit = 0;
		private MyLock lock;
		
		private MyInnerThread(MyLock l, int limit) {
			lock = l;
			this.limit = limit;
		}		

		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					long time1 = 0;
					long time2 = 0;
					Thread.currentThread().interrupt();
					try {
						time1 = System.currentTimeMillis();
						// sleep 不会使线程失去对监视器的锁定！
						Thread.sleep(limit);
						time2 = System.currentTimeMillis();
					} catch (InterruptedException e) {
						// 当线程状态为中断时调用sleep()会抛出该异常
						logger.info("A interrupted exception occur by sleep()!!");
					}
					logger.info("{} is it holde lock? {}", Thread.currentThread().getName(), (time2 - time1));
					logger.info("is this thread interrupt? {}", Thread.currentThread().isInterrupted());
				}
			}
		}
	}

	// 使用了synchronized 修饰符的方法的锁定监视器都是所在的类(this)，即同一个类，所有在方法上用了synchronized修饰符的方法都是同步的!!
	// @Test
	void modifierTest() {
		MyThread mt = new MyThread();
		Thread t1 = new Thread(() -> {
			while (!Thread.interrupted()) {
				mt.modifierTest2();
			}

		});
		Thread t2 = new Thread(() -> {
			while (!Thread.interrupted()) {
				mt.modifierTest1();
			}
		});
		t1.start();
		t2.start();
		int i = 0;
		while (t1.isAlive() && t2.isAlive()) {
			try {
				logger.info("t1 state: {}", t1.getState());
				logger.info("t2 state: {}", t2.getState());
				logger.info("{}", i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			if (i == 3) {
				// 对于对Thread对象调用 interrupt方法，若线程处于可运行状态(runnable)，则仅仅是对该线程添加interrupt标记，
				// 需要其主动中断
				// 若线程因为sleep或wait等方法而处于阻塞状态，则调用interrupt会使其抛出InterruptedException
				// 异常，
				// 可捕获该异常从而结束该线程
				t1.interrupt();
				logger.info("is t1 interrupted? {}", t1.isInterrupted());
				t2.interrupt();
				logger.info("is t2 interrupted? {}", t2.isInterrupted());

				logger.info("is alive? {}", t1.isAlive());
				logger.info("is alive? {}", t2.isAlive());
			}
		}
	}
}
