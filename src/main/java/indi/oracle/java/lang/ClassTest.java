package indi.oracle.java.lang;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
    @Disabled
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
    
    // 测试用法
    @Test
//    @Disabled
    void isAssignableFromTest() {
        // 判断被调对象是否为另一个类的父类或接口
        // 可以理解为用于判断“是否被实现”
        System.out.println(ArrayList.class.isAssignableFrom(List.class));// println: false
        System.out.println(List.class.isAssignableFrom(ArrayList.class));// println: true
        System.out.println(Collection.class.isAssignableFrom(ArrayList.class));// println: true
    }
    
    /** 
     * 测试用获取类的各种名称
     * 
     * @author DragonBoom
     * @since 2020.02.23
     */
    @Test
    @Disabled
    void nameTest() {
        Class<? extends ClassTest> class1 = this.getClass();
        System.out.println(class1.getSimpleName());
        System.out.println(class1.getName());
    }

}
