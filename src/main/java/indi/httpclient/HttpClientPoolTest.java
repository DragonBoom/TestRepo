package indi.httpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HttpClientPoolTest {
	private static HttpClientConnectionManager poolManager;

	@BeforeAll
	static void initPool() {
		poolManager = new PoolingHttpClientConnectionManager(1000, TimeUnit.SECONDS);
	}

	@Test
	void poolTest() {
		HttpRoute route = null;
		try {
			System.out.println(InetAddress.getLocalHost());
			System.out.println(InetAddress.getLoopbackAddress());
			
			route = new HttpRoute(new HttpHost("www.baidu.com", 443), InetAddress.getLocalHost(), true);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ConnectionRequest request = poolManager.requestConnection(route, null);
		HttpClientConnection con = null;
		try {
			con = request.get(0, null);
			HttpContext ctx = new BasicHttpContext();
			poolManager.connect(con, route, 100, ctx);
			if (!con.isOpen()) {
				System.out.println("Connection no opened!");
				return;
			} else {
				System.out.println("Open Connection success!");
			}
			HttpGet get = new HttpGet("/");
			con.sendRequestHeader(get);
			con.flush();
	
			System.out.println(con.receiveResponseHeader());
		} catch (ConnectionPoolTimeoutException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@AfterAll
	static void over() {
	}
}
