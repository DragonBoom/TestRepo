package indi.oracle.java.lang;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ObjectTest {

    /**
     * 存在两个不同的对象，他们的hashCode相同
     */
    @Test
    @Disabled
    void duplicateHashCodeTest() {
        String s1 = "Aa";
        String s2 = "BB";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        Assertions.assertEquals(s1.hashCode(), s2.hashCode());
        
        // 测试HashMap
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(s1, "s1");
        hashMap.put(s2, "s2");
        System.out.println(hashMap.get(s1));// case s1
        // 即hashCode相同不会使HashMap工作异常
        /*
         * ps. 哈希值相同应该会使两个对象被散列到相同的散列表中
         */
        
    }
    
    @Test
    void newObjectTest() {
        String str0 = "123";
        String str1 = new String("123");
        System.out.println(str0 == str1);// means str0 == "123" 
        System.out.println(str0 == "123");// means str1 != "123" 
    }
}
