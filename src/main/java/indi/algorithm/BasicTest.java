package indi.algorithm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class BasicTest {

    /**
     * 测试取余相关
     */
    @Test
    void remainderTest() {
        System.out.println(1%10);// case: 1
        System.out.println(10%10);// case: 0
    }
}
