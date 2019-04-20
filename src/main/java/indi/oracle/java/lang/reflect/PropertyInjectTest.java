package indi.oracle.java.lang.reflect;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

/**
 * 测试将对象“注入”到另一个对象的属性中
 * 
 * @author Think
 *
 */
@ExtendWith(TestSeparateExtension.class)
public class PropertyInjectTest {
    private MyClass mc;

    /**
     * 测试将MyClass实例注入到本类中
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    @Test
    void go() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Class<?> thisClass = this.getClass();
        Field[] fields = thisClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            MyClass myClass = new MyClass();
            myClass.setName("gello");
            field.set(this, myClass);
        }
        System.out.println(mc);
    }
}