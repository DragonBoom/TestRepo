package indi.fasterxml.jackson;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {

    @Test
    void go() throws IOException {
        String str = "{\"123\":\"ww\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tree = objectMapper.readTree(str);
        System.out.println(tree.get("123"));
    }
}
