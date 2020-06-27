package indi.oracle.java.nio;

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
     * 找出传入路径比当前路径多出来的部分
     * 
     * 若两个路径没有相同部分，则将抛异常
     */
    @Test
    @Disabled
    void releativeTest() {
        System.out.println(path1.relativize(path3));// case: t
//        System.out.println(path1.relativize(path2));// case: java.lang.IllegalArgumentException: 'other' is different type of Path
        System.out.println(path3.relativize(path1));// case: ..
        System.out.println(path1.resolve(path1.relativize(path3)));// case: w\f\..
    }
    
    @Test
    @Disabled
    void newPathTest() {
        Path path = Paths.get("");// 不会报错
        System.out.println(path);
    }
    
    @Test
    void getParentTest() {
        Path path = Paths.get("d:/");
        System.out.println(path = path.getParent());// print: null
        System.out.println(path = path.getParent());
        System.out.println(path = path.getParent());
    }
}
