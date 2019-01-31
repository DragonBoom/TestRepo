package indi.oracle.java.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class PathTest {
    Path path1 = Paths.get("d:", "w");
    Path path2 = Paths.get("d:", "w", "f");
    
    /**
     * convert 即拼接
     */
    @Test
    void convertTest() {
        System.out.println(path1);
        System.out.println(path1.resolve("f"));
    }
    
    @Test 
    void resolveTet() {
        System.out.println(path1.resolve(path2));
    }

}
