package indi.google.guava;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.io.Files;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class FilesTest {

    /**
     * touch-触摸：创建空文件 或 更新文件的最后修改时间
     */
    @Test
    @Disabled
    void touchTest() throws IOException {
        Files.touch(new File("e://test.xls"));
    }
    
    /**
     * 创建给定路径的所有所有上级路径，但不会创建给定路径本身
     */
    @Test
    void createParentDirsTest() throws IOException {
        Files.createParentDirs(new File("e://a/b/c/d/"));
    }
}
