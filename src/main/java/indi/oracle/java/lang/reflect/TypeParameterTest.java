package indi.oracle.java.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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
    void getTypeParameterTest() {
        List<String> list = new LinkedList<>();
        Arrays.stream(list.getClass().getGenericInterfaces()).forEach(System.out::println);
        /*
         * case:
         * java.util.List<E>
         * java.util.Deque<E>
         * interface java.lang.Cloneable
         * interface java.io.Serializable
         */
    }

    @Test
    @Disabled
    void go2() {
        InnerClass<String> inner = new InnerClass<>();
        Type genericSuperclass = inner.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    // correct
    @Test
    @Disabled
    void go3() {
        InnerClass<String> inner = new InnerClass<String>() {};
        Type genericSuperclass = inner.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    // correct
    @Test
    @Disabled
    void typeReferenceTest() {
        TypeReference<List<String>> ref = new TypeReference<List<String>>() {};
        System.out.println(ref.getType());
    }
    
    private List<String> filed;
    
    /**
     * 获取泛型实例域的类型
     * 
     * Type instanceof Class = true
     * 
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    @Test
    void test4() throws NoSuchFieldException, SecurityException {
        Field field = this.getClass().getDeclaredField("filed");
        judgeFieldType(field);// case: filed is List<String>;
    }
    
    /**
     * {@link https://fengyilin.iteye.com/blog/2341382}
     */
    private static void judgeFieldType(Field field) {  
        String name = field.getName();
        System.out.println(field.getType());
        System.out.println(field.getGenericType());
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) field.getGenericType();

            // 判断具体类的类型

            if (pt.getRawType().equals(List.class)) {

                // 判断泛型类的类型
                if (pt.getActualTypeArguments()[0].equals(String.class)) {
                    System.out.println(name + " is List<String>;");
                } else if (pt.getActualTypeArguments()[0].equals(int.class)
                        || pt.getActualTypeArguments()[0].equals(Integer.class)) {
                    System.out.println(name + " is List<int>;");
                }
            }
        } else if (field.getGenericType().equals(String.class)) {
            System.out.println(name + " is String;");
        } else if (field.getGenericType().equals(int.class) || field.getGenericType().equals(Integer.class)) {
            System.out.println(name + " is int;");
        }
    }  

    class InnerClass<T> extends InnerInnerClass<T> {

    }

    class InnerInnerClass<T> {

    }
}
