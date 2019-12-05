package indi.oracle.java.lang.reflect;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class FieldTest {

    @Test
    void go() {
        Set<String> targetFields = 
                Arrays.stream(MyClass.class.getDeclaredFields()).map(field -> field.getName()).collect(Collectors.toSet());
        System.out.println(targetFields);
    }
}
