package indi.oracle.java.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

/**
 * Collections 是Java自带的Collection工具类
 *
 */
@ExtendWith(TestSeparateExtension.class)
public class CollectionsTest {

    @Test
    @Disabled
    void go() {
        // addAll
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set, "wahaha", "yes", "add success!");
        System.out.println(set);
        // singleton Collection
        List<String> singletonList = Collections.singletonList("ww");
        System.out.println(singletonList);
    }
    
    @Test
    void toArrayTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        
        Object[] array = list.toArray();// 该数组类型为Object[] 而不是 String[]!!!
        
        System.out.println(array);
        
        System.out.println((String[])array);// case: java.lang.ClassCastException:
    }
}
