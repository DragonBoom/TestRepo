package indi.fasterxml.jackson;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Disabled
    void go() throws IOException {
        String str = "{\"123\":\"ww\"}";
        JsonNode tree = objectMapper.readTree(str);
        System.out.println(tree.get("123"));
    }
    
    @Test
    void emptyTest() throws JsonParseException, JsonMappingException, IOException {
        TestClass e = objectMapper.readValue("{}", TestClass.class);
        System.out.println(e);
    }
}
