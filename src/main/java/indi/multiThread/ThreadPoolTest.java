package indi.multiThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class ThreadPoolTest {
	private final static int MAX_THREADS = 5;
	private int coreSize;
	private int size;
	private long aliveSecond;

	@Before
	public void beforeEach() {
		System.out.println("```````````````````````");
		coreSize = 10;
		size = 10;
		aliveSecond = 60;
	}

	@Test
	public void orderTest1() {
		ExecutorService es = Executors.newCachedThreadPool();
		int i = 10;
		while (i > 0) {
			es.submit(new MyThread(i));
			i--;
		}
		while (Thread.activeCount() != 0L) {
			es.shutdown();
			try {
				Thread.sleep(1000);
				Thread[] t2 = new Thread[Thread.activeCount()];
				Thread.enumerate(t2);
				for (Thread t : t2) {
					System.out.println(t);
				}
				if (es.isTerminated()) {
					return;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void orderTest2() {
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
		ExecutorService es = new ThreadPoolExecutor(coreSize, size, aliveSecond, TimeUnit.SECONDS,
				queue);
		int s = 10;
		while (s > 0) {
			es.submit(new MyThread(s));
			s--;
		}

		while (Thread.activeCount() != 0L) {
			es.shutdown();
			try {
				Thread.sleep(1000);
				Thread[] t2 = new Thread[Thread.activeCount()];
				Thread.enumerate(t2);
				for (Thread t : t2) {
					System.out.println(t);
				}
				if (es.isTerminated()) {
					return;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void orderTest3() {
		BlockingQueue<Runnable> queue = new PriorityBlockingQueue<>(100, null);
		ExecutorService es = new ThreadPoolExecutor(coreSize, size, aliveSecond, TimeUnit.SECONDS,
				queue);
		int s = 10;
		while (s > 0) {
			es.submit(new MyThread(s));
			s--;
		}

		while (Thread.activeCount() != 0L) {
			es.shutdown();
			try {
				Thread.sleep(1000);
				Thread[] t2 = new Thread[Thread.activeCount()];
				Thread.enumerate(t2);
				for (Thread t : t2) {
					System.out.println(t);
				}
				if (es.isTerminated()) {
					return;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class MyThread implements Runnable, Comparable<MyThread> {
		private int id = 0;

		private MyThread(int i) {
			this.id = i;
		}

		@Override
		public void run() {
			System.out.println(id + " " + Thread.currentThread());
		}

		@Override
		public int compareTo(MyThread o) {
			return this.id - o.id;
		}

	}
}
