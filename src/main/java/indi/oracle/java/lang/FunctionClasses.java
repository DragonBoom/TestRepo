package indi.oracle.java.lang;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

class FunctionClasses {

    @Test
    void go() {
        Function<String, Integer> f = i -> 1;
        Class<? extends Function> class1 = f.getClass();
        System.out.println(class1);
    }
}
