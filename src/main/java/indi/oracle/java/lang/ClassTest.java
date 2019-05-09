package indi.oracle.java.lang;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class ClassTest {
    
    @Test
    void fieldTest() {
        Field[] fields = "".getClass().getFields();
        Arrays.stream(fields).forEach(System.out::println);
    }

}
