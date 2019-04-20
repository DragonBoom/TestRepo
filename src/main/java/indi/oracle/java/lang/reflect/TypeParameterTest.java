package indi.oracle.java.lang.reflect;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.type.TypeReference;

import indi.util.extension.TestSeparateExtension;

/**
 * 似乎没有直接获取到实例的泛型参数的方法
 * 
 * <p>
 * 参考 {@link com.fasterxml.jackson.core.type.TypeReference}
 * 
 * <p>根据{@link #go2()}、{@link #go3()}方法，似乎只有匿名内部类能访问到泛型参数
 * 
 * @author Think
 *
 */
@ExtendWith(TestSeparateExtension.class)
public class TypeParameterTest {

    /*
     * 测试如何获取泛型参数
     */

    @Test
    void getTypeParameter() {
        List<String> list = new LinkedList<>();
        Type genericSuperclass = list.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    @Test
    void go2() {
        InnerClass<String> inner = new InnerClass<>();
        Type genericSuperclass = inner.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    // correct
    @Test
    void go3() {
        InnerClass<String> inner = new InnerClass<String>() {};
        Type genericSuperclass = inner.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    // correct
    @Test
    void typeReferenceTest() {
        TypeReference<List<String>> ref = new TypeReference<List<String>>() {};
        System.out.println(ref.getType());
    }

    class InnerClass<T> extends InnerInnerClass<T> {

    }

    class InnerInnerClass<T> {

    }
}
