package indi.oracle.java.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.io.Files;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class FileTest {

    /**
     * 测试new File 对象后，删除源文件，file对象是否还能正常工作
     * @throws IOException 
     */
    @Test
    @Disabled
    void go() throws IOException {
        File file1 = new File("e://test.txt");
        File file2 = new File("e://test.txt");
        file2.delete();
        List<String> readLines = Files.readLines(file1, Charset.defaultCharset());// java.io.FileNotFoundException: e:\test.txt (系统找不到指定的文件。)
        System.out.println(readLines);
    }
    
    @Test
    void existsTest() {
        /**
         * 测试当文件的上级路径不存在时，调用exists接口是否会报错 N
         */
        File file = new File("g:/e");
        System.out.println(file.exists());// println: false
    }
}
