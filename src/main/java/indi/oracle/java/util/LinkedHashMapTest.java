/**
 * 
 */
package indi.oracle.java.util;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import lombok.SneakyThrows;

/**
 * @author wzh
 * @since 2021.06.12
 */
@ExtendWith(TestSeparateExtension.class)
class LinkedHashMapTest {
    LinkedHashMap<String, String> map = new LinkedHashMap<>();// 默认按插入顺序排序

    @Test
    @SneakyThrows
    @Disabled
    void orderTest() {
        // 可调整插入顺序，查看输出是否发生变化，以此判断是否有序
        map.put("3", "3.1");
        map.put("2", "2.1");
        map.put("1", "1.1");
        
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + " " + e.getValue());
        });
    }
    
    // 测试结果：与HashMap相同，键、值都可为Null
    @Test
    void nullTest() {
        map.put("ff", null);
        System.out.println(map.get("ff"));
        map.put(null, "ff");
        System.out.println(map.get(null));
    }
}
