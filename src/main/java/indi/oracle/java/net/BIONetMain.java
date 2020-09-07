package indi.oracle.java.net;

import java.io.IOException;
// Blocking I/O
public class BIONetMain {

	// BIO 多线程测试
	public static void main(String[] args) throws IOException {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					BIONetServer.start(BIONetServer.PORT);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread t2 = new Thread() {
			public void run() {
				try {
					BIONetClient c = new BIONetClient(BIONetClient.IP, BIONetClient.PORT);
					c.send("ok?");
					c.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		};
		t2.start();
		
		Thread t3 = new Thread() {
			public void run() {
				try {
					BIONetClient c = new BIONetClient(BIONetClient.IP, BIONetClient.PORT);
					c.send("ok 2?");
					c.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t3.start();
		
		while (Thread.activeCount() > 1) {
			try {
				System.out.println("Till have " + Thread.activeCount() + " threads...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
