package indi.apache.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.junit.jupiter.api.Test;

public class PoolingHttpClientConnectionManagerTest {

	@Test
	void main() {
		PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
		CookieStore store = new BasicCookieStore();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(store)
				.setConnectionManager(manager).build();
		HttpGet get = new HttpGet("http://www.baidu.com");
		try {
			PoolStats stats = manager.getTotalStats();
			System.out.println("pool stats:" + manager.getTotalStats());
			CloseableHttpResponse rsp = client.execute(get);
			
			rsp.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				client.close();
				System.out.println("close complete");
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}
		System.out.println(store);
	}
}
