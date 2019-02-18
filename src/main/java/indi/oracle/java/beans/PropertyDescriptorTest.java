package indi.oracle.java.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ExtendWith(TestSeparateExtension.class)
class PropertyDescriptorTest {
    MyBean b1 = new MyBean("方法", null, 1);

    MyBean b2 = new MyBean(null, 23L, 2);

    /**
     * 测试构造函数的使用
     * 
     * @throws IntrospectionException
     */
    @Test
//    @Disabled
    void constructorTest() throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("getStatus", MyBean.class);
        System.out.println(propertyDescriptor);
        Method readMethod = propertyDescriptor.getReadMethod();
        System.out.println(readMethod);

        PropertyDescriptor propertyDescriptor2 = new PropertyDescriptor("getStatus", MyBean.class, "isGetStatus", null);
        System.out.println(propertyDescriptor2);
    }

    @Test
    @Disabled
    void PropertyEditorTest() throws IntrospectionException {
        PropertyDescriptor pd1 = new PropertyDescriptor("name", MyBean.class);
        System.out.println(pd1.getPropertyEditorClass());// null
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyBean {
        private String name;
        private Long order;
        private int getStatus;
    }
}
