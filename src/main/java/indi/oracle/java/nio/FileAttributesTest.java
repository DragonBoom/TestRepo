package indi.oracle.java.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import lombok.SneakyThrows;

/**
 * 测试 【文件属性】 相关内容
 * 
 * @author DragonBoom
 *
 */
@ExtendWith(TestSeparateExtension.class)
class FileAttributesTest {

    /**
     * 测试windows系统中快捷方式的可能的影响
     * 
     * @throws IOException 
     */
    @Test
    @Disabled
    void linkTest() throws IOException {
        // 找出快捷方式 
        Path dir = Paths.get("e:", "for test", "link file test");// 该目录下包含了快捷方式
        Files.list(dir)
                .forEach(System.out::println);
        /*
         * 可知，快捷方式的文件名形如:test.txt.lnk，即可通过后缀是否为lnk来进行分辨
         * 接下来，查看快捷方式文件的属性
         */
        Files.list(dir)
                // 注意：Path的endsWith与String的不一样，Path的endsWith比较的是最后一个路径结点是否与给定字符串一致
                .filter(path -> path.getFileName().toString().endsWith(".lnk"))
                .findFirst()
                .ifPresent(path -> {
                    BasicFileAttributeView fileAttributeView = 
                            Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
                    BasicFileAttributes attributes = null;
                    try {
                        attributes = fileAttributeView.readAttributes();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(attributes.isRegularFile());// true
                    System.out.println(attributes.isSymbolicLink());// false
                    System.out.println(attributes.isOther());// false
                    /**
                     * 由上可知，windows的快捷方式对于Java也只是普通的文件罢了
                     */
                });
        
    }
    
    /**
     * 测试修改创建时间
     * 
     * @since 2021.08.04
     */
    @Test
    @SneakyThrows
    void modifyTimeTest() {
        BasicFileAttributeView attributeView = Files.getFileAttributeView(Paths.get("e:", "test.txt"), BasicFileAttributeView.class);
        // 修改：最后修改时间，最后访问时间，创建时间
        attributeView.setTimes(FileTime.fromMillis(System.currentTimeMillis()), FileTime.fromMillis(System.currentTimeMillis()), FileTime.fromMillis(System.currentTimeMillis()));
    }
}
