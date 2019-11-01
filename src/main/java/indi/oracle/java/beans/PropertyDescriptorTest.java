package indi.oracle.java.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;

import indi.util.extension.TestSeparateExtension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ExtendWith(TestSeparateExtension.class)
class PropertyDescriptorTest {
    MyBean b1 = new MyBean("方法", null, null, 1);

    MyBean b2 = new MyBean(null, null, 23L, 2);

    MyBean2 b21 = new MyBean2("测试", null, 23L, 2);
    /**
     * get开头的方法
     * 
     * @throws IntrospectionException
     */
    @Test
    @Disabled
    void getMethodTest() throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", MyBean.class);
        System.out.println(propertyDescriptor);// java.beans.PropertyDescriptor[name=name; propertyType=class java.lang.String; readMethod=public java.lang.String indi.oracle.java.beans.PropertyDescriptorTest$MyBean.getName(); writeMethod=public void indi.oracle.java.beans.PropertyDescriptorTest$MyBean.setName(java.lang.String)]
        Method readMethod = propertyDescriptor.getReadMethod();
        System.out.println(readMethod);// public java.lang.String indi.oracle.java.beans.PropertyDescriptorTest$MyBean.getName()
        System.out.println(propertyDescriptor.getDisplayName());// name
        System.out.println(propertyDescriptor.getName());// name
        System.out.println(propertyDescriptor.attributeNames());// java.beans.PropertyDescriptor[name=getStatus; propertyType=int; readMethod=public int indi.oracle.java.beans.PropertyDescriptorTest$MyBean.getGetStatus()]

        PropertyDescriptor propertyDescriptor2 = new PropertyDescriptor("getStatus", MyBean.class, "isGetStatus", null);
        System.out.println(propertyDescriptor2);
    }
    
    @Test
    @Disabled
    void springBeanUtilsTest() {
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(MyBean.class);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor.getName());// name
            System.out.println(propertyDescriptor.getDisplayName());// name
        }
    }

    @Test
    @Disabled
    void PropertyEditorTest() throws IntrospectionException {
        PropertyDescriptor pd1 = new PropertyDescriptor("name", MyBean.class);
        System.out.println(pd1.getPropertyEditorClass());// null
    }
    
    /**
     * 测试获取不存在的属性
     * @throws IntrospectionException 
     * 
     * @since 2019.10.30
     */
    @Test
    @Disabled
    void getNotExpistsPorpertyTest() throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name3", MyBean.class);
        // throws: java.beans.IntrospectionException: Method not found: isName3
        System.out.println(propertyDescriptor);
    }
    
    /**
     * 测试能否对不同的类调用相同的PropertyDescriptor的Getter/Setter方法？N
     * 
     * @author wzh
     * @since 2019.10.30
     */
    @Test
    @Disabled
    void workForDifferrentClassTest() throws Exception {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", MyBean.class);
        Method readMethod = propertyDescriptor.getReadMethod();
        Object invoke = readMethod.invoke(b21);// case: java.lang.IllegalArgumentException: object is not an instance of declaring class
        System.out.println(invoke);
    }
    
    @Test
    void getParentPropertyTest() throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name2", MyBean.class);
        System.out.println(propertyDescriptor);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper=true)
    public static class MyBean extends MyBeanFather {
        private String name;
        private String nameCAB;
        private Long order;
        private int getStatus;
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyBeanFather {
        private String name2;
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyBean2 {
        private String name;
        private String nameCAB;
        private Long order;
        private int getStatus;
    }
}
