package indi.oracle.java.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class FilesTest {

    /**
     * 对于move方法，在windows系统下，文件夹可以在同一个盘符下移动而不报错， 但当文件夹需要跨盘移动时就会报
     * java.nio.file.DirectoryNotEmptyException: d:\test 异常
     * 
     * <p>windows下不支持StandardCopyOption.COPY_ATTRIBUTES选项
     */
    @Test
    @Disabled
    void moveTest() throws IOException {
        Files.move(Paths.get("e:", "test"), Paths.get("d:", "test"));// java.nio.file.DirectoryNotEmptyException: d:\test
    }
    
    @Test
    void deleteInNotExistDirectoryTest() throws IOException {
        Files.delete(Paths.get("e:\\a\\b\\c\\d.a"));// case: throw NoSuchFileException: e:\a\b\c
    }
}
