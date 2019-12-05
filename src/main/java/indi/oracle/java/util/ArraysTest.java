package indi.oracle.java.util;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class ArraysTest {

    /**
     * 测试java自带的在数组中查询的工具
     * 
     */
    @Test
    void searchTest() {
        String[] strs = new String[] {"ff", "ww"};
        
        System.out.println(Arrays.binarySearch(strs, "ff"));// print: 0
        System.out.println(Arrays.binarySearch(strs, "ff2"));// print: -2
        System.out.println(Arrays.binarySearch(strs, "ffw"));// print: -2
        
        System.out.println("--");
        /*
         * 原数组必须是有序的，否则无法正常工作：
         */
        strs = new String[] {"ff", "ff", "ww2", "yy", "ff", "ww"};

        System.out.println(Arrays.binarySearch(strs, "ff"));// print: 0
        System.out.println(Arrays.binarySearch(strs, "ww"));// print(error): -3
        System.out.println(Arrays.binarySearch(strs, "ff2"));// print: -3
        System.out.println(Arrays.binarySearch(strs, "ffw"));// print: -3
    }
}
