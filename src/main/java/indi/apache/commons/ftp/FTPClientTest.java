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
    @Disabled
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
    @Disabled
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
    @Disabled
    void noBufferTest() throws IOException {
        FTPClient ftpClient = getConnection();
        
        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            ftpClient.retrieveFile(absolutePath, out);
        }
        System.out.println(ftpClient.getReplyString());
    }
    
    // 测试能否自动创建上级目录 Y （这个似乎主要依赖于服务器的实现，可能存在有些服务器可以自动创建上级目录，有些不可以）
    @Test
    @Disabled
    void ftpMkdirTest() throws IOException {
        FTPClient ftpClient = getConnection();
        
        boolean result = ftpClient.makeDirectory("/upload/gas/test/test");
        System.out.println(result);
        System.out.println(ftpClient.getReplyString());
    }
    
    // 测试重复切换目录
    @Test
    @Disabled
    void changeWorkingDirectoryTest() throws IOException {
        FTPClient ftpClient = getConnection();
        boolean isSuccess = ftpClient.changeWorkingDirectory("./gen");// 无法用./前缀指向绝对路径！（windows）
        System.out.println(isSuccess);
        isSuccess = ftpClient.changeWorkingDirectory("./java-source");// false!!!
        System.out.println(isSuccess);
    }
    
    // 测试分隔符能否不是“/” ，可以是“\”（FileZilla Server可以，可能因服务器而异）
    @Test
    void  separatorTest() throws IOException {
        FTPClient ftpClient = getConnection();
        boolean isSuccess = ftpClient.changeWorkingDirectory("./gen");// 无法用./前缀指向绝对路径！（windows）
        System.out.println(isSuccess);// print: true
        // 进入上级目录（根目录也能调用，工作路径无变化）
        isSuccess = ftpClient.changeWorkingDirectory("..");
        System.out.println(isSuccess);// print: true
        System.out.println(ftpClient.printWorkingDirectory());
        isSuccess = ftpClient.changeWorkingDirectory("..");// print: /
        System.out.println(isSuccess);// print: true
        System.out.println(ftpClient.printWorkingDirectory());// print: /
        isSuccess = ftpClient.changeWorkingDirectory("\\flv");
        System.out.println(isSuccess);// print: true
        System.out.println(ftpClient.printWorkingDirectory());// print: /flv
        
    }
    
    private FTPClient getConnection() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("127.0.0.1");
        int reply = ftpClient.getReplyCode();
        if (FTPReply.isPositiveCompletion(reply)) {
            if (ftpClient.login("admin", "admin")) {
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

