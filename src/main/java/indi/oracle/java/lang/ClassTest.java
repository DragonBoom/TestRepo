package indi.oracle.java.lang;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import lombok.Getter;
import lombok.Setter;

@ExtendWith(TestSeparateExtension.class)
public class ClassTest {

    @Test
    @Disabled
    void fieldTest() {
        Field[] fields = "".getClass().getFields();
        Arrays.stream(fields).forEach(System.out::println);
    }

    /**
     * 测试父类的this
     */
    @Test
    void superClassThisTest() {
        Parent.Child child = new Parent.Child();
        
        child.printThis();// print indi.oracle.java.lang.ClassTest$Parent$Child@365c30cc
        // 即父类中的this指的是子类。。。
    }

    @Getter
    @Setter
    public static class Parent {
        public String name;

        public void printThis() {
            System.out.println(this);
        }

        @Getter
        @Setter
        public static class Child extends Parent {
            public String name;

        }
    }

}
