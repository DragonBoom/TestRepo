package indi.google.guava.collection;

import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.collect.ImmutableTable;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class TableTest {
    ImmutableTable<String, String ,String> EMPTY_IMMUTABLE_TABLE = ImmutableTable.of();

    /**
     * 测试不可修改表的对空行/空列的操作：返回空对象而不是null
     * 
     * @since 2019.11.01
     */
    @Test
    @Disabled
    void emptyEmmutableHandleTest() {
        Map<String, String> column = EMPTY_IMMUTABLE_TABLE.column("test");
        System.out.println(column == null);// print: false
        System.out.println(column);// print: {}
    }
    
    @Test
    void rowColumnTest() {
        ImmutableTable<Object, Object, Object> table = ImmutableTable.builder()
                .put(1, 1, "a")
                .put(1, 2, "b")
                .put(1, 3, "c")
                .put(1, 4, "d")
                .put(2, 1, "e")
                .put(2, 2, "f")
                .put(2, 3, "g")
                .put(2, 4, "h")
                .build()
                ;
        System.out.println(table);// print: {1={1=a, 2=b, 3=c, 4=d}, 2={1=e, 2=f, 3=g, 4=h}}
        System.out.println(table.row(1));// print: {1=a, 2=b, 3=c, 4=d}
        System.out.println(table.column(1));// print: {1=a, 2=e}
    }
}
