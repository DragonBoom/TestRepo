package indi.oracle.java.lang.reflect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ClassLoaderTest {

    @Test
    void loadClassFromBytesTest() throws IOException, InstantiationException, IllegalAccessException {
        MyClassLoader myClassLoader = new MyClassLoader();// define class loader
        byte[] bytes = Files.readAllBytes(Paths.get("d:", "CbpmCalendarDO.class"));

        Class<?> class1 = myClassLoader.loadClass("com.hauxsoft.entity.CbpmCalendarDO", bytes);// load it
        System.out.println(class1);
        Object newInstance = class1.newInstance();
        System.out.println(newInstance);
    }
}
