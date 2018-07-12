package indi.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 协议：
 * 客户端、服务器约好,每次通信，在发送的内容前附带一个类似于HTTP头字段Content-Length的4字节字段，用于描述将要发送的消息（内容）的长度
 * 
 * @author 13124
 *
 */
public class BIONetServer {
	public static final int PORT = 9901;
	private static ServerSocket server;
	private static ReentrantLock lock = new ReentrantLock(false);
	private static ExecutorService es = Executors.newFixedThreadPool(100);
	private static boolean close = false;
	private static final Logger logger = LoggerFactory.getLogger("BIOServer-TCP");

	public static void start(int port) throws IOException, InterruptedException {
		logger.info("--server start--");

		lock.lockInterruptibly();
		try {
			if (server != null) {
				return;
			}
		} finally {
			lock.unlock();
		}
		// 初始化
		server = new ServerSocket(port);

		while (!close) {
			// The method blocks until a connection is made
			Socket socket = server.accept(); // 此时已建立起TCP连接
			logger.info("accept a connection at {} {}", socket.getInetAddress(), socket.getPort());
			Thread t1 = new Thread(new SocketHandler(socket));
			es.submit(t1);
		}
	}

	private static class SocketHandler implements Runnable {
		private Socket socket;

		public SocketHandler(Socket s) {
			socket = s;
		}

		@Override
		public void run() {
			InputStream inStream = null;
			OutputStream outStream = null;
			try {
				inStream = socket.getInputStream();
				outStream = socket.getOutputStream();
				String request = null;
				byte[] buffer = null;
				StringBuilder response = null;
				while (true) {
					// 依约定，从左到右读取消息长度
					int length = 0;
					int temp = inStream.read();
					if (temp == -1)
						return;
					length += temp << 24;
					for (int i = 2; i >= 0; i--) {
						temp = inStream.read();
						length += temp << (i * 8);
					}
					
					logger.info("papare to read a byte array with length: {}", length);
					buffer = new byte[length];
					inStream.read(buffer);
					request = new String(buffer, "utf-8");

					response = new StringBuilder();
					logger.info("服务器收到消息： {}", request.toString());
					response.append("receive : ");
					response.append(request);
					// outStream.write(response.toString().getBytes("utf-8"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 虚拟机无法通过垃圾回收释放这些资源
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
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 为了加快GC？
					socket = null;
				}
			}
		}
	}
}
