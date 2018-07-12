package indi.multiThread;

public final class MyThread extends Thread {
	int i = 0;
	
	public synchronized void  modifierTest1() {
		i++;
		System.out.println(i);
	}
	
	public synchronized void  modifierTest2() {
		i++;
		System.out.println(i);
	}
	
	public synchronized void whoIsMonitor() {
		System.out.println(Thread.holdsLock(this));
	}
	
	public synchronized void  waitTest() {
		try {
			Thread.currentThread().wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;
		System.out.println(i);
	}

	@Override
	public void run() {
		try {
			int i = 5;
			while (i > 0) {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread());
				i--;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
