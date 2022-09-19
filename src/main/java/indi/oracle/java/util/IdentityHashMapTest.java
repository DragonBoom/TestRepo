/**
 * 
 */
package indi.oracle.java.util;

import java.util.IdentityHashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

/**
 * @author wzh
 * @since 2022.01.04
 */
@ExtendWith(TestSeparateExtension.class)
class IdentityHashMapTest {

    @Test
    void stringTest() {
        IdentityHashMap<String, Object> map = new IdentityHashMap<>();
        
        map.put("ff", 123);
        
        System.out.println(map.containsKey("ff"));
    }
}
