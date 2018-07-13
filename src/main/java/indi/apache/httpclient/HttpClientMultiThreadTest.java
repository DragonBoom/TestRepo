package indi.apache.httpclient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

public class HttpClientMultiThreadTest {

	public static void main(String[] args) {
		go();
	}

	@Test
	static void go() {
		PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
		// 设置默认每个路由（相当于每个主机ip）的连接上限 ?
		manager.setDefaultMaxPerRoute(50);
		manager.setMaxTotal(500);
		ConnectionKeepAliveStrategy keepAliveStrategy = (response, context) -> {
			return 60;
		};
		CloseableHttpClient client = HttpClientBuilder.create().setConnectionManager(manager)
				.setKeepAliveStrategy(keepAliveStrategy).build();

		String[] urls = { "http://www.pixiv.com", "http://www.baidu.com", "http://www.baidu.com",
				"http://www.baidu.com", "http://www.bilibili.com", "http://www.douyu.com" };
		for (int i = 0; i < urls.length; i++) {
			TestThread tt = null;
			tt = new TestThread(client);
			tt.setUrl(urls[i]);
			new Thread(tt).start();
		}
		while (Thread.activeCount() > 1) {
			try {
				Thread.sleep(2000);
				System.out.println(TestThread.rsps);
				System.out.println(TestThread.rsps.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class TestThread implements Runnable {
		private CloseableHttpClient client;
		private String url;
		private final static List<CloseableHttpResponse> rsps = new LinkedList<>();

		TestThread(CloseableHttpClient client) {
			this.client = client;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public void run() {
			HttpGet get = new HttpGet(url);
			try {
				CloseableHttpResponse rsp = null;
				try {
					rsp = client.execute(get);
				} catch (HttpHostConnectException e2) {
					return;
				}
				String result = EntityUtils.toString(rsp.getEntity(), "UTF-8");
				EntityUtils.consume(rsp.getEntity());
				System.out.println("```" + result);
				rsps.add(rsp);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
