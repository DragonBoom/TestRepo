package indi.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BIONetClient {
	public final static int PORT = 9901;
	public final static String IP = "127.0.0.1";
	private String ip = IP;
	private int port = PORT;
	private Socket socket = null;
	private String toWrite = null;
	private boolean close = false;

	public BIONetClient(String ip, int port) throws UnknownHostException, IOException {
		this.ip = ip;
		this.port = port;
		init();
	}

	private void init() {
		ClientHandler ch = new ClientHandler(ip, port);
		Thread t = new Thread(ch);
		t.start();
	}

	public void send(String body) {
		toWrite = body;
	}

	public void close() {
		close = true;
	}

	private class ClientHandler implements Runnable {

		private ClientHandler(String ip, int port) {
			try {
				socket = new Socket(ip, port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// Creates a stream socket and connects it to the specified port
			// number
			// on the named host.
			InputStream inStream = null;
			OutputStream outStream = null;
			try {
				inStream = socket.getInputStream();
				outStream = socket.getOutputStream();
				// 客户端阻塞，直至准备发送消息或被关闭；消息发送完毕后才会关闭
				while (toWrite == null) {
					if (close)
						return;
					System.out.println(Thread.currentThread() + " 等待输入中" + toWrite);
					Thread.sleep(1000);
				}
				// 取前4字节（每字节8位）记录消息长度
				int len = toWrite.length();
				for (int i = 3; i >= 0; i--) {
					outStream.write(len >> (i * 8));
				}
				outStream.write(toWrite.getBytes("utf-8"));
				// 发送
				outStream.flush();
				// socket.shutdownInput();
				System.out.println("消息发送成功，准备读取响应");
				// 根据约定，从左到右开始，从消息前4个字节中读取消息长度
				int length = 0;
				int temp = inStream.read();
				if (temp == -1)
					return;
				length += temp << 24;
				for (int i = 1; i < 4; i++) {
					temp = inStream.read();
					length += temp << ((3 - i) * 8);
				}
				System.out.println("length: " + length);
				byte[] bytes = new byte[length];
				inStream.read(bytes);
				String response = new String(bytes, "utf-8");
				System.out.println("收到回应： " + response);
				socket.close();
				System.out.println("A client end");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				toWrite = null;
				// 释放GC无法回收的资源
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (inStream != null) {
					try {
						inStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (outStream != null) {
					try {
						outStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
