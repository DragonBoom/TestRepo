package indi.oracle.java.util.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import indi.util.TestUtils;

/**
 * 可重入锁
 * 
 * @author 13124
 *
 */
public class ReentrantLockTest {
	private static final Logger logger = LoggerFactory.getLogger("");

	@Test
	@Disabled
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
	
	/**
	 * 测试使用Lock.Condition
	 * 
	 * 打印结果为：
	 * <pre>
Thread ID = 12 1593938920484
Thread ID = 13 1593938921484
Thread ID = 1
Thread ID = 12 1593938920484
Thread ID = 13 1593938921484
     * </pre>
     * 
	 * @author DragonBoom
	 * @since 2020.07.05
	 * @throws InterruptedException
	 */
	@Test
	@Disabled
	void conditionTest() throws InterruptedException {
	    ReentrantLock lock = new ReentrantLock();
	    Condition condition = lock.newCondition();
	    
	    Runnable run1 = () -> {
	        lock.lock();
	        try {
	            long i = System.currentTimeMillis();
	            System.out.println("Thread ID = " + Thread.currentThread().getId() + " " + i);
	            // 等待直至被唤醒或中断，将释放锁
	            TimeUnit.SECONDS.sleep(1);
	            condition.await();
	            System.out.println("Thread ID = " + Thread.currentThread().getId() + " " + i);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally {
	            lock.unlock();
	        }
	    };
	    
	    new Thread(run1).start();
	    
	    new Thread(run1).start();
	    
	    TimeUnit.SECONDS.sleep(4);
	    lock.lock();
	    try {
	        System.out.println("Thread ID = " + Thread.currentThread().getId());
	        condition.signal();// 唤醒一个线程，必须持有锁才能调用
	        condition.signal();// 唤醒一个线程，必须持有锁才能调用
	    } finally {
	        lock.unlock();
	    }
	    TimeUnit.SECONDS.sleep(4);

	}
	
	private LinkedList<String> store = new LinkedList<>();
	
	/**
	 * 通过一个锁的多个Condition实现了线程同步：每一时刻只执行了一个线程
	 * 
	 * @author DragonBoom
	 * @since 2020.07.05
	 */
	@Test
    void conditionConsumerProducerTest() {
        ReentrantLock lock = new ReentrantLock();
        Condition productCondition = lock.newCondition();// 生产条件
        Condition consumeCondition = lock.newCondition();// 消费条件
        
        
        // 消费者
        Runnable consum = () -> {
            while (true) {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(2);
                    
                    System.out.println(Thread.currentThread().getId() + "消费：" + store.poll());
                    
                    // 唤醒生产
                    productCondition.signal();
                    // 等待生产
                    consumeCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        // 生产者
        Runnable product = () -> {
            while (true) {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(2);
                    String produce = Long.toString(System.currentTimeMillis());
                    store.push(produce);
                    System.out.println(Thread.currentThread().getId() + "生产了：" + produce);
                    
                    // 唤醒消费
                    consumeCondition.signal();
                    // 等待消费
                    productCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        
        new Thread(product).start();
        new Thread(product).start();
        
        new Thread(consum).start();
        new Thread(consum).start();
        new Thread(consum).start();
        new Thread(consum).start();
        
        TestUtils.holdUntil(() -> false, 500, 100000);
        
    }
}

