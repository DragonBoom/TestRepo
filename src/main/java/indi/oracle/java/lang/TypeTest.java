package indi.oracle.java.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

@ExtendWith(TestSeparateExtension.class)
public class TypeTest {

    /**
     * 测试泛型类类型转化的一些问题
     * <p>
     * 该方法跑得通...
     * 这是因为：
     * <li>1.有泛型擦除，MyClass<\Integer>、MyClass<\String>最终都会被当作 MyClass
     * <li>2.Java中的类型检测(checkcast)只检测对变量的使用是否符合变量的声明，而不管变量引用的对象如何
     */
    @Test
//    @Disabled
    void go() {
        MyClass<String> myClass = new MyClass<String>();
        myClass.setObj("ww");
        MyClass<?> str = myClass;
        MyClass<Integer> integer = (MyClass<Integer>) str;
//        integer.setObj(123);// ！不会报错
        System.out.println(integer.getObj());// 若再调用getClass方法就会报错
        System.out.println(integer.getObj().getClass());// 字节码中多了checkcast
    }
    
    /**
     * 测试泛型擦除：利用反射可以向泛型集合插入不符合要求的元素
     * 
     * @since 2022-09-19
     */
    @Test
    @SneakyThrows
    @Disabled
    void genericClearTest() {
        HashSet<String> set = new HashSet<>();
        set.add("happy");
        
        Method method = set.getClass().getMethod("add", Object.class);
        method.invoke(set, 123);
        Assertions.assertEquals("[happy, 123]", set.toString());
        // 会报错：
//        set.forEach(a -> {
//            System.out.println(a.getClass());
//        });
    }
    
    /**
     * 该方法内将看到字节码：checkcast。且checkcast共出现3次。checkcast将会导致ClassCastException
     * 
     * @since 2022-09-19
     */
    @Test
    @SneakyThrows
    @Disabled
    void byteCodeTest() {
        List<Integer> list = new ArrayList();
        list.getClass().getMethod("add", Object.class).invoke(list, "a");

        // part a 直接get的情况，外层方法需要Object参数
        System.out.println(list.get(0));
        // part b 使用Object接， 在对obj调用方法
        Object obj = list.get(0);
        System.out.println(obj.getClass());

        // part c 演示强转的字节码
        String a = (String) obj;// 第1次checkcast

        // part d get后直接调用方法
        System.out.println(list.get(0).getClass());// 报错，第2次checkcast
        System.out.println(list.get(0).toString());// 第3次checkcast
        // part e get后先强转Obj， 再调用方法
        System.out.println(((Object) list.get(0)).getClass());// 无checkcast
    }

    public static class MyClass<T> {
        T obj;

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }

        public void clear() {
            obj = null;
        }
    }
}
