package indi.algorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import redis.clients.util.Hashing;

/**
 * 散列后再次散列
 * 
 * @author Think
 *
 */
@ExtendWith(TestSeparateExtension.class)
public class PostHashTest {
    
    @Test
    @Disabled
    void go() {
        int hashed = 987;
        
        int postHash = hashed;
        System.out.println(postHash);
    }
    
    @Test
    void generateHash() throws IOException {
        // 48 - 57
        // 10
        // [480 - 570]
        long hash = Hashing.MD5.hash(Files.readAllBytes(Paths.get("E:", "test.xls")));
        String hashStr = Long.toString(hash);// .length = 10
        int sum = hashStr.chars().sum();
        System.out.println(hash);
        System.out.println(hashStr.length());
        System.out.println(sum);
        
    }

}
