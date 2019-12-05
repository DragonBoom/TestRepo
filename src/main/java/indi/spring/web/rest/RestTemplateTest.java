package indi.spring.web.rest;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.google.common.io.Files;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class RestTemplateTest {
    RestTemplate restTemplate = new RestTemplate();

    @Test
    @Disabled
    void go() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://www.baidu.com", String.class);
//        System.out.println(responseEntity);
        try {
            ResponseEntity<String> responseEntity2 = restTemplate.getForEntity("http://192.168.1.123", String.class);
            System.out.println(responseEntity2);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 测试用RestTemplate下载
     * 
     * <p>经测试，可用RestTemplate下载文件
     */
    @Test
    @Disabled
    void downloadTest() throws IOException {
        String url = "http://down10.zol.com.cn/xiazai/utorrentv3.5.5.44876.exe";
        byte[] bytes = restTemplate.getForObject(url, byte[].class);
        Files.write(bytes, new File("e://f.exe"));
        
    }
    
    @Test
    @Disabled
    void exceptionTest() {
        String url = null;
        try {
            url = "http://10.10.10.10";
            String obj = restTemplate.getForObject(url, String.class);
            System.out.println(obj);
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("无法访问 " + url);
        }
    }
}
