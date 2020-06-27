package indi.lombok;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class EqualsAndHashCodeTest {

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
    
    @Test
    void go() {
        MyClass myClass1 = new MyClass("f");
        MyClass myClass12 = new MyClass("f");
        
        System.out.println(myClass1.equals(myClass12));// true
        
        MyClass2 myClass2 = new MyClass2("f");
        MyClass2 myClass22 = new MyClass2("f");
        
        System.out.println(myClass2.equals(myClass22));// false
    }
}
