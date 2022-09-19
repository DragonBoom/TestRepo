/**
 * 
 */
package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

/**
 * @author wzh
 * @since 2021.02.04
 */
@ExtendWith(TestSeparateExtension.class)
class ProcessBuilderTest {

    @Test // 77936339
    @SneakyThrows
    void chcpTest() {
        String[] commands = new String[] { "cmd.exe", "chcp", "65001" };
        ProcessBuilder processBuilder = new ProcessBuilder(commands).inheritIO();// 可以理解为先执行后挂起？

        Process process = processBuilder.start();
//        InputStream inputStream = process.getInputStream();
//        System.out.println(IOUtils.toString(inputStream));
        String[] commands2 = new String[] { "exiftool", 
                "\"F:\\byCrawler\\pixiv\\74387334_p0.jpg\"", 
                "-comment=\"ぷぅ崎ぷぅ奈\"",
                "-overwrite_original",// 不保留原文件
                "-charset", "utf8"
                };
        Process process2 = new ProcessBuilder(commands2).start();
        process2.waitFor();
        System.out.println("over");
//        process.waitFor();
//        OutputStream outputStream = process.getOutputStream();
//        outputStream.write("exiftool F:\\byCrawler\\pixiv\\74387334_p0.jpg -comment=\"ぷぅ崎ぷぅ奈\" -overwrite_original".getBytes());
//        outputStream.flush();
//        process.waitFor();
//        
//        InputStream inputStream = process.getInputStream();
//        
//        process.waitFor();
//        System.out.println(IOUtils.toString(inputStream));

    }

}
