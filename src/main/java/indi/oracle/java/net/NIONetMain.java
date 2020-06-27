package indi.oracle.java.net;

import java.io.IOException;
import java.net.UnknownHostException;

public class NIONetMain {

	public static void main(String[] args) throws UnknownHostException, IOException {
		NIONetServer.start(NIONetServer.PORT);
		BIONetClient client = new BIONetClient(BIONetClient.IP, BIONetClient.PORT);
		client.send("fff");
	}
}
