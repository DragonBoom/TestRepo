package indi.oracle.java.lang;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

/**
 * 泛型测试
 */
@ExtendWith(TestSeparateExtension.class)
public class GenericTest {

    /**
     * alias = facker
     * 
     * <p>测试假泛型
     */
    @Test
    void pseudoTest() {
        List<String> list = new LinkedList<>();
        Integer i = 1;
        Object o = i;
        System.out.println(list.getClass());
        list.add((String) o);// 报类型转换异常
    }
}
