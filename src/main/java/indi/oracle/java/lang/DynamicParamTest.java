package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class DynamicParamTest {
    
    @Test
    void emptyTest() {
        dynamicMethod();
        // println: [Ljava.lang.String;@6e2c9341
        // println: 0
    }
    
    void dynamicMethod(String...args) {
        System.out.println(args);
        System.out.println(args.length);
    }

}
