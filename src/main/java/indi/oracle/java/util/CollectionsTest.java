package indi.oracle.java.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Collections 是Java自带的Collection工具类
 *
 */
public class CollectionsTest {

    @Test
    void go() {
        // addAll
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set, "wahaha", "yes", "add success!");
        System.out.println(set);
        // singleton Collection
        List<String> singletonList = Collections.singletonList("ww");
        System.out.println(singletonList);
    }
}
