package indi.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

class HttpSocketTest {

	@Test
	void sendHttp() {
		String baiduHost = "14.215.177.38";
		int httpPort = 80;
		int httpsPort = 443;
		try {
			Socket socket = new Socket(baiduHost, httpPort);
			BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.println("hello");
			ps.flush();
			StringBuilder sb = new StringBuilder();
			String tmp = null;
			while ((tmp = br.readLine()) != null) {
				sb.append(tmp);
			}
			System.out.println(sb.toString());
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
