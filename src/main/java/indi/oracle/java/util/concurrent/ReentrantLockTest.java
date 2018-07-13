package indi.oracle.java.util.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 可重入锁
 * 
 * @author 13124
 *
 */
public class ReentrantLockTest {
	private static final Logger logger = LoggerFactory.getLogger("");

	@Test
	public void whatTest() {
		// 默认为不公平锁:若只有某一线程在竞争锁，则將独占线程设置为该线程，该线程直接获得锁
		// 锁定原理：利用CAS方法判断volatile修饰的整型state是否为预期值（0），
		// 若是则将其修改为目标值（1），并将独占线程设置为当前线程，不中断线程，相当于直接获得锁
		// 若state不为1，则执行继承自AQS的acquire()方法：
		//     尝试获取锁，若获取不到锁，则根据当前线程与相应模式创建新结点并入队，之后中断当前线程
		ReentrantLock unFairLock = new ReentrantLock();
		// 公平锁：所有线程必须遵循先入先出原则，排队获取锁
		ReentrantLock fairLock = new ReentrantLock(true);
		LockKeeper ck = new LockKeeper(fairLock);
		LockTester ct = new LockTester(ck);
		Thread t1 = new Thread(ct);
		Thread t2 = new Thread(new LockTester(new LockKeeper()));
		t1.start();
		t2.start();
	}

	// 测试锁在多线程环境下如何工作的内部类
	class LockKeeper {
		Lock lock = null;
		int i = 0;

		LockKeeper() {
			lock = new ReentrantLock();
		}

		LockKeeper(Lock lock) {
			this.lock = lock;
		}

		void go() {
			lock.lock();
			i++;
			lock.unlock();
		}

		void result() {
			logger.info("Thread {}, result is {}", Thread.currentThread().getName(), i);
		}
	}

	class LockTester implements Runnable {
		LockKeeper lk = null;

		LockTester(LockKeeper lk) {
			this.lk = lk;
		}

		@Override
		public void run() {
			int m = 0;
			while (true) {
				lk.go();
				lk.result();
				m++;
				if (m % 5 == 0) {
					logger.info("---");
				}
			}
		}
	}

	// 测试可重入锁源码中用到的一些算法、技巧
	// @Test
	void algorithm() {
		int i = 0, i2 = 1;
		// 等同于 若i!=1, i2 = 3, i = 1,若i==1, i1、i2不变
		if (!(i == 1) && (i2 = 3) == 3) {
			i = 1;
		}
		logger.info("result: {} {}", i, i2);
		
	}
}
