package indi.spring.web.rest;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.google.common.io.Files;

public class RestTemplateTest {

//    @Test
    void go() {
        RestTemplate restTemplate = new RestTemplate();
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
     * @throws IOException 
     */
    @Test
    void downloadTest() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://down10.zol.com.cn/xiazai/utorrentv3.5.5.44876.exe";
        byte[] bytes = restTemplate.getForObject(url, byte[].class);
        Files.write(bytes, new File("e://f.exe"));
        
    }
}
