package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class NullTest {

    /**
     * 测试基础数据类型与其封装类间的转换时，封装类为null的情况
     */
    @Test
    void basicTypeNullTest() {
        boolean b = new Integer(1) == (Integer) null;
        System.out.println("done 1");
        b = 1 == (Integer) null;// case NullPointerException
        System.out.println("done 2");
    }
    
}
