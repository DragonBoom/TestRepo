package indi.spring.http;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseEntityTest {

    @Test
    void go() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("e:", "皓轩-日常规范2018.docx"));
        
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(bytes);
        System.out.println(str);
        Object readValue = mapper.readValue(str, new TypeReference<byte[]>() {});
        System.out.println(readValue);
        Files.write(Paths.get("e:", "123.docx"), bytes);
    }
}
