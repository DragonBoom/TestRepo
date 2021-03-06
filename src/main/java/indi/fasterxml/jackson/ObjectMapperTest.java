package indi.fasterxml.jackson;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import indi.util.TestObjects.DatePropClass;
import indi.util.extension.TestSeparateExtension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Disabled
    void jsonArray2JavaArrayTest() throws JsonParseException, JsonMappingException, IOException {
        String json = "[1, 2, 3]";
        Long[] array = mapper.readValue(json, Long[].class);
        System.out.println(array.getClass());
        System.out.println(array);// print: [Ljava.lang.Long;@6adbc9d
    }
    
    @Test
    @Disabled
    void notEmptyFieldTest() throws JsonProcessingException {
        StringPropClass obj = new StringPropClass("test", null);
        /*
         * Convenience method, equivalent to calling: 
         * setPropertyInclusion(JsonInclude.Value.construct(incl, incl));
         */
        mapper.setSerializationInclusion(Include.NON_EMPTY);

        System.out.println(mapper.writeValueAsString(obj));
    }
    
    @Test
    void convertValueTest() {
        // 1. convert 2 map test
        // a. str
        StringPropClass strClass = new StringPropClass("arg1", "arg2");
        Map<?, ?> map = mapper.convertValue(strClass, Map.class);
        System.out.println(map.get("id"));// print arg1
        // b. date
        DatePropClass datePropClass = new DatePropClass();
        datePropClass.setDate(new Date());
        map = mapper.convertValue(datePropClass, Map.class);
        System.out.println(map.get("id"));// print: null
        // WARN:
        System.out.println(map.get("date"));// print: 1577163565439
        System.out.println(map.get("date").getClass());// print: class java.lang.Long
        
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StringPropClass {
        String id;
        String name;
    }
    
    @Data
    public static class IntegerPropClass {
        Integer id;
    }
    
}
