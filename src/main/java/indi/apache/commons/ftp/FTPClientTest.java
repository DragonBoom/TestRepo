package indi.apache.commons.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class FTPClientTest {
    private String absolutePath = "/dev/tomcat/shared/commons-collections4-4.0.jar";
    private File outputFile = new File("e:/ftp.dat");
    
    // 测试登陆花费的时间
    @Test
    void loginTest() throws IOException {
        getConnection();// 183 millis
    }

    @Test
    @Disabled
    void errorMsgTest() throws SocketException, IOException {
        FTPClient ftpClient = getConnection();
        
        // 测试直接获取文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("e:/ftp.dat"));
        ftpClient.retrieveFile(absolutePath, fileOutputStream);
        System.out.println(Arrays.toString(ftpClient.getReplyStrings()));
        fileOutputStream.close();
        
        // 测试删除ftp文件（无权限）
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
    
    /*
     * 比较使用/不使用缓存时从FTP上下载文件所需的时间
     * 
     * buffer：13 s
     * no buffer：13s
     */
    @Test
    void bufferTest() throws IOException {
        FTPClient ftpClient = getConnection();
        
        int bufferLen = 1024 * 1024 * 10;
        ftpClient.setBufferSize(bufferLen);
        
        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            BufferedOutputStream bufferedOut = new BufferedOutputStream(out, bufferLen);// default 8197: 8 * 1024 + 5
            ftpClient.retrieveFile(absolutePath, bufferedOut);
        }
        System.out.println(ftpClient.getReplyString());
    }
    
    @Test
    void noBufferTest() throws IOException {
        FTPClient ftpClient = getConnection();
        
        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            ftpClient.retrieveFile(absolutePath, out);
        }
        System.out.println(ftpClient.getReplyString());
    }
    
    private FTPClient getConnection() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("www.hauxsoft.com");
        int reply = ftpClient.getReplyCode();
        if (FTPReply.isPositiveCompletion(reply)) {
            if (ftpClient.login("hauxsoft", "hauxsoft")) {
                ftpClient.setControlEncoding("utf-8"); // 编码
                ftpClient.enterLocalPassiveMode(); // 设置PassiveMode传输
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); // 二进制流方式
                ftpClient.setBufferSize(1024); // 缓存
                ftpClient.setDataTimeout(3000); // 超时时间
            }
        }
        return ftpClient;
    }
}

