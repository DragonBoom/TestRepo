package indi.oracle.java.lang.exception;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.oracle.java.lang.exception.NullExceptionTest.MyClass;
import indi.util.extension.TestSeparateExtension;
import lombok.Getter;
import lombok.Setter;

@ExtendWith(TestSeparateExtension.class)
public class NullExceptionTest {

    @Test
    void go() throws IllegalAccessException, InvocationTargetException {
        MyClass myClass = new MyClass();
        BeanUtils.setProperty(myClass, "name", null);
    }
    
    @Getter
    @Setter
    public static class MyClass {
        private String name;
    }
}
