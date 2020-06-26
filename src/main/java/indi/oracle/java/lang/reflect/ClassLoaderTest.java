package indi.oracle.java.lang.reflect;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.collect.ImmutableSet;

import indi.util.ReflectUtils;
import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ClassLoaderTest {

    /**
     * 测试自定义类加载器并加载类
     * 
     * @author DragonBoom
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    @Disabled
    void loadClassFromBytesTest() throws IOException, InstantiationException, IllegalAccessException {
        MyClassLoader myClassLoader = new MyClassLoader();// define class loader
        byte[] bytes = Files.readAllBytes(Paths.get("d:", "CbpmCalendarDO.class"));

        Class<?> class1 = myClassLoader.loadClass("com.hauxsoft.entity.CbpmCalendarDO", bytes);// 自己是实现的方法，用于加载类
        System.out.println(class1);
        Object newInstance = class1.newInstance();
        System.out.println(newInstance);
    }
    
    @Test
    void getAllClassesTest() throws ClassNotFoundException, URISyntaxException, IOException {
        // 可见：
        ImmutableSet<Class<?>> allClasses = ReflectUtils.getAllClasses("indi.oracle.java.lang.reflect");
        System.out.println(allClasses);
    }
}
