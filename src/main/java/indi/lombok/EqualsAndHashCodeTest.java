package indi.lombok;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class EqualsAndHashCodeTest {
    
    @Test
    void useCaseTest() {
        // 使用@EqualsAndHashCode
        MyClass myClass1 = new MyClass("f");
        MyClass myClass12 = new MyClass("f");
        
        Assertions.assertEquals(myClass1, myClass12);
        // 不使用@EqualsAndHashCode，将采用hashcode比较
        MyClass2 myClass2 = new MyClass2("f");
        MyClass2 myClass22 = new MyClass2("f");

        Assertions.assertNotEquals(myClass2, myClass22);
    }
    
    @Test
    void complexPropTest() {
        // 测试能否依据值域中的集合，来判断对象是否相同 Y
        Assertions.assertEquals(new MyClass3(new LinkedList<>()), new MyClass3(new LinkedList<>()));
        // 测试能否依据值域中的对象，来判断对象是否相同 Y
        Assertions.assertNotEquals(new MyClass4(new MyClass3(new LinkedList<>())), 
                new MyClass4(new MyClass3(Arrays.asList("String"))));
    }

    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    private static class MyClass {
        private String name;
    }
    
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    private static class MyClass2 {
        private String name;
    }
    
    @EqualsAndHashCode
    @Setter
    @ToString
    @AllArgsConstructor
    public static class MyClass3 {
        private List<String> list;
    }
    
    @EqualsAndHashCode
    @Setter
    @ToString
    @AllArgsConstructor
    public static class MyClass4 {
        private MyClass3 c;
    }
    
}
