package indi.oracle.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class FileChannelTest {

	private final static String src = "D:\\eclipse-java-mars-R-win32-x86_64.zip";
	private final static String dst = "D:\\test\\eclipse-java-mars-R-win32-x86_64.zip";
	private final static int part = 1024 * 1024;

	/**
	 * Files 与FileChannel 效率比较
	 */
	@Test
	public void test() {
		Path p = Paths.get(src);
		Path p2 = Paths.get(dst);
		byte[] bytes = null;
		long t1 = System.currentTimeMillis();
		// Files
		try {
			bytes = Files.readAllBytes(p);
			Files.write(p2, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
		
		// 单线程FileChannel
		long t3 = System.currentTimeMillis();
		long t31 = 0L;
		FileChannel fc = null;
		long size = 0L;
		try {
			fc = FileChannel.open(p2);
			size = fc.size();
			ByteBuffer bb = ByteBuffer.allocateDirect((int) size);
			fc.read(bb);
			bb.flip();
			fc = FileChannel.open(p, StandardOpenOption.READ, StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
			t31 = System.currentTimeMillis();
			// 最耗时的操作(?)
			fc.write(bb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long t4 = System.currentTimeMillis();
		System.out.println("  " + (t31 - t3));
		System.out.println("  " + (t4 - t31));

		// 多线程 FileChannel
		long t5 = System.currentTimeMillis();
		MyLock myLock = new MyLock();
		try {
			size = fc.size();
			int n = (int) ((size % part == 0) ? size / part : size / part + 1);
			ByteBuffer[] bbs = new ByteBuffer[n];
			int i = 0;
			// 创建线程池
			ExecutorService es = Executors.newFixedThreadPool(10);
			while (i < n) {
				bbs[i] = ByteBuffer.allocateDirect(part);
				int position = i * part;
				ReadThread r = new ReadThread(fc, bbs[i], (long) position, myLock);
				es.submit(r);
				i++;
			}
			while (myLock.checkLock()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long t6 = System.currentTimeMillis();
		System.out.println(t6 - t5);
	}

	private class MyLock {
		private boolean lock = true;

		public void unlock() {
			lock = false;
		}

		public boolean checkLock() {
			return lock;
		}
	}

	private class ReadThread implements Runnable {
		private FileChannel fc;
		private ByteBuffer bb;
		private long position;
		private MyLock myLock;

		public ReadThread(FileChannel fc, ByteBuffer bb, long position, MyLock myLock) {
			this.fc = fc;
			this.bb = bb;
			this.position = position;
			this.myLock = myLock;
		}

		@Override
		public void run() {
			try {
				int read = fc.read(bb, position);
				bb.flip();
				if (read != part) {
					System.out.println("finally read: " + read);
					myLock.unlock();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
