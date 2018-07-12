package indi.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIONetServer {
	public final static int PORT = 9901;
	private static ServerHandler serverHandler = null;
	private static final Logger logger = LoggerFactory.getLogger("NIO-Server");

	public static synchronized void start(int port) {
		if (serverHandler != null) {
			serverHandler.stop();
		}
		serverHandler = new ServerHandler(port);
		new Thread(serverHandler, "NIO-Server").start();
	}

	private static class ServerHandler implements Runnable {
		private boolean started = false;
		private Selector selector = null;

		public ServerHandler(int port) {
			try {
				// 创建选择器
				selector = Selector.open();
				// 打开监听通道
				ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
				// 设置非阻塞(默认为true，即阻塞)
				serverSocketChannel.configureBlocking(false);
				// 绑定端口
				serverSocketChannel.bind(new InetSocketAddress(port));
				// 向选择器注册该通道“感兴趣”的事件，监听客户端连接请求，并返回相应令牌
				serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
				started = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void stop() {
			started = false;
		}

		@Override
		public void run() {
			while (started) {
				try {
					// 阻塞,直至至少有一个对该I/O操作“感兴趣”的channel被选中(轮询)
					selector.select(1000);
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = keys.iterator();
					logger.info("Selected Keys size: {}", keys.size());
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();
						logger.info("The channel create this key is {} with opSet {}",
								key.channel(), key.interestOps());
						handleInput(key);
						iterator.remove();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void handleInput(SelectionKey key) throws IOException {
			SelectableChannel sc = null;
			if (key.isValid()) {
				sc = key.channel();
			} else {
				return;
			}
			if (sc instanceof ServerSocketChannel) {
				ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
				// 通过ServerSocketChannel
				// 的accept方法建立SocketChannel实例，相当于完成了TCP三次握手，建立了TCP连接
				SocketChannel socketChannel = serverSocketChannel.accept();
				socketChannel.configureBlocking(false);
				// 通道向选择器注册读操作
				socketChannel.register(selector, SelectionKey.OP_READ);
			}
			if (key.isReadable() && sc instanceof SocketChannel) {
				SocketChannel socketChannel = (SocketChannel) key.channel();
				ByteBuffer lengthBuffer = ByteBuffer.allocate(4);
				int readResult = socketChannel.read(lengthBuffer);
				if (readResult == -1)
					return;
				byte[] lenBytes = lengthBuffer.array();
				int length = 0;
				for (int i = 0; i < 4; i++) {
					length += lenBytes[i] << ((3 - i) * 8);
				}
				logger.info("server receive message length: {}", length);
				ByteBuffer bb = ByteBuffer.allocate(length);
				socketChannel.read(bb);
				bb.flip();
				byte[] bytes = bb.array();
				String result = new String(bytes, "UTF-8");
				logger.info("Server receive：{} ", result);
				doRead(result);
				String toWrite = "hello";
				length = toWrite.length();
				lenBytes = new byte[4];
				
				for (int i = 0; i < 4; i++) {					
					for (int j = (3 - i) * 8, m = 0; m < 4; j++, m++) {
						int temp = 1 << j;
						if ((temp & length) != 0)
							lenBytes[i] += 1 << m;
					}
				}
				logger.info("server response length: {}", length);
				bb = ByteBuffer.wrap(lenBytes);
				// 发送消息内容长度
				socketChannel.write(bb);
				// 发送消息
				doWrite(socketChannel, toWrite);
				// 关闭链路，释放资源
				key.cancel();
				socketChannel.close();
			}
		}

		private void doRead(String read) {

		}

		private void doWrite(SocketChannel socketChannel, String toWrite) throws IOException {
			byte[] bytes = toWrite.getBytes();
			ByteBuffer bb = ByteBuffer.wrap(bytes);
			socketChannel.write(bb);
		}
	}
}
