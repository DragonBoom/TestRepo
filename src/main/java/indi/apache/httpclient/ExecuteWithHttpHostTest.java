package indi.apache.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ExecuteWithHttpHostTest {

    @Test
    void go() throws ClientProtocolException, IOException {
        PoolingHttpClientConnectionManager conManager = new PoolingHttpClientConnectionManager();
        CloseableHttpClient client = HttpClientBuilder.create().setConnectionManager(conManager)
                .build();
        HttpGet httpGet = new HttpGet("https://www.google.com/");
        
        // set request headers
        httpGet.addHeader(new BasicHeader("Host", "www.google.com"));// is it work?
        httpGet.addHeader(new BasicHeader("Connection", "keep-alive"));
        httpGet.addHeader(new BasicHeader("Accept-Encoding", "gzip, deflate, br"));
        httpGet.addHeader(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"));
        httpGet.addHeader(new BasicHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36"));

        HttpHost proxyHost = new HttpHost("localhost", 1080, "http"); // TODO not work...
        HttpHost defaultHost = new HttpHost("www.google.com", 443, "https"); // work well
        
        // build request config
        RequestConfig config = RequestConfig.custom().setProxy(proxyHost).build(); // 目前只发现用该种方式使用代理
        httpGet.setConfig(config);

        CloseableHttpResponse response = client.execute(defaultHost, httpGet); // 这里的HttpHost target无法用于代理...

        // print request headers
        System.out.println("> request headers:");
        for (Header header : httpGet.getAllHeaders()) {
            System.out.println(" " + header.getName() + " " + header.getValue());
        }
        // print response headers
        System.out.println("> response headers:");
        for (Header header : response.getAllHeaders()) {
            System.out.println(" " + header.getName() + " " + header.getValue());
        }
        System.out.println(EntityUtils.toString(response.getEntity()).length());
        client.close();
    }
}
