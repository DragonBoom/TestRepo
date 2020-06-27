package indi.json;

import java.io.IOException;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class JsonArrayTest {

    @Test
    @Disabled
    void start() {
        String jsonArrayStr = "[\"ff\", \"ww\"]";
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    @Disabled
    void listMap2ArrayTest() throws JsonProcessingException {
        
        ImmutableMap<Object,Object> multimap = ImmutableMap.builder()
                .put("wa", "hh")
                .put("wa2", "hh")
                .put("wa3", "hh")
                .build();
        Lists.newArrayList(multimap);
        System.out.println(multimap);
        ObjectMapper mapper = new ObjectMapper();
        
        System.out.println(mapper.writeValueAsString(multimap));
    }
    
    @Test
    void jsonStr2MapTest() throws JsonParseException, JsonMappingException, IOException {
        String json = "[{\"表单id\":{\"表单字段1\":\"表单字段1的值\",\"表单字段2\":\"表单字段2的值\",\"表单字段n\":\"表单字段n的值\"}}]";
        ObjectMapper mapper = new ObjectMapper();
        LinkedList<?> readValue = mapper.readValue(json, LinkedList.class);
        System.out.println(readValue);
        System.out.println(readValue.getClass());
        System.out.println(readValue.get(0).getClass());
    }
}
