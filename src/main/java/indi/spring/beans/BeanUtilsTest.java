package indi.spring.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Maps;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class BeanUtilsTest {

    //    @Test
    void go() {
        //        BeanUtils.getPropertyDescriptors(List.class);
    }
    
    @Test
    void nullOverrideTest() {
        Value v1 = new Value();
        v1.setName("wahahah");
        System.out.println(v1);
        
        Value v2 = new Value();
        BeanUtils.copyProperties(v2, v1);
        
        System.out.println(v1);
    }

    @Test
    @Disabled
    void mapBeanTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map<String, String> map = Maps.newHashMap();
        map.put("id", "123");
        map.put("name", "wahaha");
        Value v = new Value();
        // Spring的BeanUtils不支持从map复制
        BeanUtils.copyProperties(map, v);
        System.out.println(v);
        // Spring的BeanUtils仅支持简单的bean复制
        Value v2 = new Value();
        v2.id = "123";
        v2.name = "32";
        BeanUtils.copyProperties(v2, v);
        System.out.println(v);
    }

    public static class Value {
        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Value [name=");
            builder.append(name);
            builder.append(", id=");
            builder.append(id);
            builder.append("]");
            return builder.toString();
        }

    }
}
