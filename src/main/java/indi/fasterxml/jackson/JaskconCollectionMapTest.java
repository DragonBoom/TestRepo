package indi.fasterxml.jackson;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JaskconCollectionMapTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String jsonArray = "[\"ff\",\"ww\"]";
    private static final String jsonArray2 = "[{\"name\":null,\"id\":123},{\"name\":null,\"id\":123}]";

    @Test
    void go() {
        try {
            Collection<?> collection = mapper.readValue(jsonArray, HashSet.class);
            System.out.println(collection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void go2() throws JsonProcessingException {
        List<MyClass> mcs = new LinkedList<>();
        MyClass mc1 = new MyClass();
        mc1.setId(123L);
        String json = mapper.writeValueAsString(mcs);
        System.out.println(json);
    }

    @Test
    void go3() throws JsonParseException, JsonMappingException, IOException {
        Object o = mapper.readValue(jsonArray2, new TypeReference<MyClass[]>() {
        });
        System.out.println(o);
    }

    public static class MyClass {
        private String name;
        private Long id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    }
}
