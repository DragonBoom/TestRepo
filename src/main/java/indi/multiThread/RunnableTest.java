package indi.multiThread;

/**
 * synchronized 测试 synchronized(obj) {};
 * 其中obj就是同步监视器，它的含义是：线程开始执行同步代码块之前，必须先获得对同步监视器的锁定。任何时刻只能有一个线程可以获得对同步监视器的锁定，
 * 当同步代码块执行完成后，该线程会释放对该同步监视器的锁定。虽然java程序允许使用任何对象作为同步监视器，但
 * 是同步监视器的目的就是为了阻止两个线程对同一个共享资源进行并发访问，因此通常推荐使用可能被并发访问的共享资源充当同步监视器。
 * 
 * @author 13124
 *
 */
public class RunnableTest {
	// IllegalMonitorStateException22 : if the current thread is not the owner
	// of the object's monitor
	public static String lock = "00";
	public static String trueLock = "1";

	public static void main(String[] args) {
		Thread t1 = new Thread() {
			public void run() {
				while (true)
					synchronized (lock) {
						if (lock.equals("1")) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							trueLock.notify();
							lock = "1";
							System.out.println(lock + " 0");
						}
					}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				Integer i = 0;
				while (true)
					synchronized (lock) {
						if (!lock.equals("1")) {
							try {
								trueLock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							lock.notify();
							lock = i.toString();
							i++;
							System.out.println(lock + " 1");
						}
					}
			}
		};
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		System.out.println(Thread.currentThread().getName());
		
		t1.start();
		t2.start();
		
		while (Thread.activeCount() != 1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
