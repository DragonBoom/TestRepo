package indi.oracle.java.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.common.io.Files;

class FileTest {

    /**
     * 测试new File 对象后，删除源文件，file对象是否还能正常工作
     * @throws IOException 
     */
    @Test
    void go() throws IOException {
        File file1 = new File("e://test.txt");
        File file2 = new File("e://test.txt");
        file2.delete();
        List<String> readLines = Files.readLines(file1, Charset.defaultCharset());// java.io.FileNotFoundException: e:\test.txt (系统找不到指定的文件。)
        System.out.println(readLines);
    }
}
