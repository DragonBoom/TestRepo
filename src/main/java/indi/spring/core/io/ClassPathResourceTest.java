package indi.spring.core.io;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * 学习如何获取当前类路径的资源
 * 
 * @author DragonBoom
 *
 */
public class ClassPathResourceTest {

    @Test
    void go() throws ClassNotFoundException {
        ClassPathResource resource = new ClassPathResource("indi.oracle.java.reflect.MyClass");
        System.out.println(resource.getClassLoader());
    }
}
