package indi.google.guava.base;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.base.Objects;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ObjectsTest {
    
    @Test
    void equalsTest() {
        // primitive test
        System.out.println(Objects.equal(null, 1));// print false
        
        int i = 0;
//        System.out.println(i == null);
        System.out.println(((Object) i)== null);// print false
    }

}
