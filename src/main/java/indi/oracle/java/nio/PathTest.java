package indi.oracle.java.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

/**
 * 值得一提的是，Path的方法可能会修改本身的路径
 * @author DragonBoom
 *
 */
@ExtendWith(TestSeparateExtension.class)
class PathTest {
    Path path1 = Paths.get("d:", "w");
    Path path2 = Paths.get("w", "f");
    Path path3 = Paths.get("d:", "w", "t");
    Path path4 = Paths.get("d:", "a.jpg");
    Path path5 = Paths.get("d:", "w", "t", "a.jpg");
    
    /**
     * resolve 也是拼接，若传入路径是绝对路径，则只会返回传入路径
     */
    @Test
    @Disabled
    void resolveTest() {
        System.out.println(path1.resolve(path2));// case: d:\w\w\f
        System.out.println(path1);
        System.out.println(path2);
    }
    
    /**
     * 获取相对路径
     * 
     * 若两个路径没有相同部分，则将抛异常
     */
    @Test
//    @Disabled
    void releativeTest() {
        System.out.println(path1.relativize(path3));// case: t
//        System.out.println(path1.relativize(path2));// case: java.lang.IllegalArgumentException: 'other' is different type of Path
        System.out.println(path3.relativize(path1));// case: ..
        System.out.println(path1.relativize(path3));// case: t
        System.out.println(path4.relativize(path3));// case: ..\w\t
        System.out.println(path3.relativize(path4));// case: ..\..\a.jpg
        System.out.println(path1.relativize(path5));// t\a.jpg
        // 由上可见，relativize方法是获取到参数路径之间的路径，因此会包含返回上级路径的写法：..\
        
        System.out.println(path1.resolve(path1.relativize(path3)));// case: d:\w\t
    }
    
    @Test
    @Disabled
    void newPathTest() {
        Path path = Paths.get("");// 不会报错
        System.out.println(path);
    }
    
    @Test
    @Disabled
    void getParentTest() {
        Path path = Paths.get("d:/");
        System.out.println(path = path.getParent());// print: null
        System.out.println(path = path.getParent());
        System.out.println(path = path.getParent());
    }
    
    @Test
    @Disabled
    void compareTest() {
        Path p1 = Paths.get("f:", "9.jpg");
        Path p2 = Paths.get("f:", "12.jpg");
        System.out.println(p1.compareTo(p2));// print: 8
        // Path的比较无法实现windows资源管理器的数值化排序的效果
    }
    
    @Test
    void parseTest() {
        Path path = Paths.get("f:\\music\\ビッケブランカ - WALK (movie ver.).lrc");
        System.out.println(Files.exists(path));
        int nameCount = path.getNameCount();
        System.out.println(nameCount);
        for (int i = 0; i < nameCount; i++) {
            System.out.println(path.getName(i));
        }
    }
}
