package indi.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.web.MockMultipartFile;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class Md5Test {
    
    /**
     * 测试guva 获取MD5的代码，直接传入整个字节数组与逐块传入字节数组的计算结果是否一致性
     * 
     * <p>结论：两种计算方式的结果相同
     * 
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     */
    @Test
    void guavaMD5ForStreamTest() throws IOException, NoSuchAlgorithmException {
        File toHashFile = new File("e:/for test/ToHash");
        FileInputStream fileInputStream = new FileInputStream(toHashFile);
        byte[] bytes = Files.readAllBytes(Paths.get("e:", "for test", "ToHash"));
        
        // 1. guava的md5
        // a. hash whole bytes
        // 1) way 1
        String digest0 = Hashing.md5().newHasher().putBytes(bytes).hash().toString();
        // 2) way 2
        String digest1 = Hashing.md5().hashBytes(bytes).toString();
        // b. hash buf byte one by one
        Hasher md5Hasher = Hashing.md5().newHasher();
        
        byte[] byteBuf = new byte[1024];
        int len = -1;
        while ((len = fileInputStream.read(byteBuf)) != -1) {
            md5Hasher.putBytes(byteBuf, 0, len);
        }
        String digest2 = md5Hasher.hash().toString();
        // 2. apache 的 API：
        String digest3 = DigestUtils.md5Hex(new FileInputStream(toHashFile));

        System.out.println(digest0);
        System.out.println(digest1);
        System.out.println(digest2);
        System.out.println(digest3);
        // 排除调用方法的差异
        System.out.println(digest0.equals(digest1));// print: true
        System.out.println(digest1.equals(digest2));// print: true
        System.out.println(digest1.equals(digest3));
    }
    
}
