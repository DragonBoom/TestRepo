/**
 * 
 */
package indi.apache.httpclient;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

/**
 * @author wzh
 * @since 2020.09.04
 */
@ExtendWith(TestSeparateExtension.class)
class HttpClientTest {

    /**
     * 测试发送x-www-form-urlencoded表单
     * 
     * @author DragonBoom
     * @since 2020.09.04
     */
    void UrlEncodedFormEntityTest() {
        HttpPost post = new HttpPost("https://accounts.google.com/o/oauth2/token");
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("grant_type", "grant_type"));
        new UrlEncodedFormEntity(nvps, Charset.forName("utf-8"));
    }
}
