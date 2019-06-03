package indi.apache.commons.ftp;

import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class FTPClientTest {

    @Test
    void go() throws SocketException, IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("www.hauxsoft.com");
        int reply = ftpClient.getReplyCode();
        System.out.println(reply);// print: 220
        if (FTPReply.isPositiveCompletion(reply)) {
            if (ftpClient.login("hauxsoft", "hauxsoft")) {
                ftpClient.setControlEncoding("utf-8"); // 编码
                ftpClient.enterLocalPassiveMode(); // 设置PassiveMode传输
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); // 二进制流方式
                ftpClient.setBufferSize(1024 * 256); // 缓存
                ftpClient.setDataTimeout(3000); // 超时时间
            }
        }
        String pathName = "tes2t.txt";
        int replyCode = ftpClient.dele(pathName);
        System.out.println(replyCode);// print: 550
        FTPFile[] listFiles = ftpClient.listFiles(pathName);
        Arrays.stream(listFiles).forEach(file -> {
            System.out.println(file);
            try {
                if (file.isFile()) {
                    int replyCode2 = ftpClient.dele(pathName);
                    
                    if (!FTPReply.isPositiveCompletion(replyCode2)) {
                        System.out.println(Arrays.toString(ftpClient.getReplyStrings()));// print: [550 Permission denied.]
                        System.out.println(replyCode2);// print 550
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ftpClient.disconnect();

    }
}
