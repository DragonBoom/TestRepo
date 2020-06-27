package indi.apache.httpclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;
import org.junit.jupiter.api.Test;

public class ExtractHostTest {

    @Test
    void go() {
        try {
            // the way HttpClient get host default
            HttpHost host = URIUtils.extractHost(new URI("https://fanyi.baidu.com"));
            StringBuilder sb = new StringBuilder("host");
            sb.append(" ").append(host.getHostName()).append(" ").append(host.getPort()).append(" ").append(host.getSchemeName());
            System.out.println(sb);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
