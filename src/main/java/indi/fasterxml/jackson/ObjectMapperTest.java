package indi.fasterxml.jackson;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ObjectMapperTest {

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
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
