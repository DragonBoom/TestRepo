package indi.google.guava;

import org.junit.jupiter.api.Test;

public class SegmentHashIndexingTest {

    /*
     * 测试Guava Cache的哈希映射
     * 
     */
    @Test
    void go() {
        Object obj =  new String("123ffffffffffffffzxc");                
        int hash = obj.hashCode();
        System.out.println("hash:" + hash);
        int size = 16;// 2^4
        int mask = 4;
        int shift = 32 - size;
        int result = hash & mask;
        System.out.println(result);
        
    }
}
