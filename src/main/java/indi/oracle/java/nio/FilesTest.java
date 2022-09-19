package indi.oracle.java.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.data.Wrapper.BooleanWrapper;
import indi.io.FileUtils;
import indi.util.extension.TestSeparateExtension;
import lombok.SneakyThrows;

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
    @Disabled
    void deleteInNotExistDirectoryTest() throws IOException {
        Assertions.assertThrows(NoSuchFileException.class, () -> {
            Files.delete(Paths.get("e:\\a\\b\\c\\d.a"));// case: throw NoSuchFileException: e:\a\b\c
        });
    }
    
    /*
     * --------------------------------------------------
     */
    
    private static Path forTestPath = Paths.get("E:\\for test");
    private static Path forTestPath2 = Paths.get("E:\\for test2");
    private static Path subFolder1Path = Paths.get("E:\\for test\\sub_folder1");
    
    @BeforeAll
    static void init() {
        // 创建测试目录
        FileUtils.createDirectoryIfNotExist(forTestPath);
        FileUtils.createDirectoryIfNotExist(subFolder1Path);
        // 创建 没有内容的测试文件
//      FileUtils.createEmptyFileIfNotExist(subFolder1Path.resolve("test.txt"));
    }
    
    /**
     * 测试什么情况下会抛 AccessDeniedException
     * 
     * <li>1. 通过流访问目录，如：Files.newDirectoryStream(Path)或Files.list(Path)，在修改目录之前没有关闭该目录下的子目录的流
     * 
     * @author DragonBoom
     * @since 2020.08.31
     */
    @Test
    @Disabled
    void accessDeniedTest() throws IOException {
        Path dest = subFolder1Path.getParent().resolve("!_" + subFolder1Path.getFileName().toString());
        // 清空移动的目标目录
        FileUtils.clearDirectory(dest, true);
//      
//        // 需要subFolder1Path下创建!_开头的非空目录
        Path subFolder2 = subFolder1Path.resolve("subFolder2");
        FileUtils.createDirectoryIfNotExist(subFolder2);
        Path subFolder3 = subFolder2.resolve("subFolder3");
        FileUtils.createDirectoryIfNotExist(subFolder3);

        // 需注意，这里模拟开启流的不是移动的目录，而是要移动目录的子目录（导致移动时无法修改该目录（但可以修改目录下的内容））
        // 如果开启的是待移动目录，线程结束后修改仍会生效
        
        // v1. 测试 Files.newDirectoryStream(subFolder2);
//        DirectoryStream<Path> stream = Files.newDirectoryStream(subFolder2);
//        stream.forEach(p -> {});// 即使执行forEach，不关闭流仍会抛异常
        // 若不关闭流，将抛 AccessDeniedException
//        stream.close();
        // v2. 测试 Files.list
        Stream<Path> listStream = Files.list(subFolder2);
        listStream.findAny();
//        System.out.println(listStream.count()); 
//        listStream.close();// 关闭流后能正常执行
//        System.out.println(Files.list(dir).count());// 该操作属于中断操作，但不会关闭流
//        System.out.println(Files.list(dir).findFirst());// 该操作属于中断操作，但不会关闭流
        
        Files.move(subFolder1Path, dest);
    }
    
    @Test
    @SneakyThrows
    @Disabled
    void sizeForDirTest() {
        Path emptyPath = Paths.get("f:", "byCrawler");
        Path notEmptyPath = Paths.get("f:", "byCrawler", "konachan");
        Assertions.assertEquals(0, Files.size(emptyPath));
        long notEmptyPathSize = Files.size(notEmptyPath);
        System.out.println(notEmptyPathSize);
        Assertions.assertTrue(notEmptyPathSize == 0);// Files.size可接收指向目录的参数，但返回值只有0
    }
    
    @Test
    @Disabled
    void listTest() throws IOException {
        Path source = Paths.get("f:", "byCrawler");
        BooleanWrapper containsFolderW = BooleanWrapper.of(false);
        try (Stream<Path> stream = Files.list(source)) {
            stream.forEach(p -> {
                Assertions.assertFalse(p.equals(source));// 不包含源目录
                if (Files.isDirectory(p)) {
                    containsFolderW.setTrue();
                }
            });
        }
        Assertions.assertEquals(true, containsFolderW.get());// 会包含子目录条目
    }
    
    @Test
    void fileVisitorTest() {
        // 经测试，用Files.walkFileTree执行FileVisitor，当对某目录执行preVisitDirectory返回FileVisitResult.SKIP_SUBTREE时，
        // 不对该目录执行FileVisitor.postVisitDirectory
    }
}
