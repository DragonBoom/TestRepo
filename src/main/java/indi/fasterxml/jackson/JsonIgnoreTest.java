package indi.fasterxml.jackson;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonIgnoreTest {

    /**
     * @JsonIgnoreProperties 注解会<b>使其注解的域的属性</b>被无视，而不是像@JsonIgnore一样使被<b>其注解的域</b>被无视
     */
    @Test
    public void simpleTest() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer();
        try {
            byte[] bytes = new String("狗比").getBytes();
            ObjectMapperTestObject tc = new ObjectMapperTestObject();
            tc.setBytes(bytes);
            tc.setId("1");
            tc.setName("狗比2");
            tc.setTestClass(new ObjectMapperTestObject());
            String json = writer.writeValueAsString(tc);
            System.out.println(json);// 有@JsonIgnore注解的bytes字段被无视，@JsonIgnoreProperties注解的testClass没有被无视
                                    // 在类上添加的@JsonIgnoreProperties({"id"}) 使得id字段被忽视
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
