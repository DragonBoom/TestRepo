package indi.oracle.java.util.funtion;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

/**
 * 使用函数式接口时需要特别注意<b>泛型</b>的使用
 * 
 */
public class NewFeaturesAtJava8Test {

    public void go() {

    }

    /**
     * 打印出
     * indi.oracle.java.util.funtion.NewFeaturesAtJava8Test$$Lambda$186/1613255205@7113b13f
     */
    @Test
    void referenceMethodTest() {
        MyClass m = new MyClass();
        Supplier<String> supplier = m::go; // 引用实例的方法作为lambda函数
        System.out.println(supplier);
    }

    static class MyClass {

        public String go() {
            System.out.println("go");
            return "go";
        }

        public String go(String go) {
            System.out.println(go);
            return go;
        }
    }
}
