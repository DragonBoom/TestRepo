package indi.fasterxml.jackson;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mysql.fabric.xmlrpc.base.Array;

import indi.util.extension.TestSeparateExtension;
import lombok.Data;

@ExtendWith(TestSeparateExtension.class)
public class ObjectMapperTest {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * 简单测试能不能把字节数组序列化 可以
     */
    @Test
    @Disabled
    void arraySerializeTest() {
        ObjectWriter writer = mapper.writer();
        try {
            byte[] bytes = new String("狗比").getBytes();
            TestClass tc = new TestClass();
            tc.setBytes(bytes);
            tc.setId("1");
            tc.setName("狗比2");
            tc.setTestClass(new TestClass());
            String json = writer.writeValueAsString(tc);
            System.out.println(json);// print: {"id":"1","bytes":"54uX5q+U","testClass":{"id":null,"bytes":null,"testClass":null}}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 测试序列化时能不能把String转化为Integer 可以
     */
    @Test
    @Disabled
    void string2IntegerTest() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
        StringPropClass stringPropClass = new StringPropClass();
        stringPropClass.id = "12";
        IntegerPropClass readValue = 
                mapper.readValue(mapper.writeValueAsString(stringPropClass), IntegerPropClass.class);
        System.out.println(readValue);// print: ObjectMapperTest.IntegerPropClass(id=12)
    }
    
    @Test
    @Disabled
    void autoDecodeTest() throws IOException {
        String json = "{\"id\": \"fff%E4%B8%AD%E6%96%87%E6%B5%8B%E8%AF%95123wa\"}";
        JsonNode jsonNode = mapper.readTree(json);
        System.out.println(jsonNode.get("id").asText());
    }
    
    /**
     * JsonArray String -> Long[]
     * 
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @Test
    void jsonArray2JavaArrayTest() throws JsonParseException, JsonMappingException, IOException {
        String json = "[1, 2, 3]";
        Long[] array = mapper.readValue(json, Long[].class);
        
        System.out.println(array);
    }
    
    @Data
    public static class StringPropClass {
        String id;
    }
    
    @Data
    public static class IntegerPropClass {
        Integer id;
    }
    
}
