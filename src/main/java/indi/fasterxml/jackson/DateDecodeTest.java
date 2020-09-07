package indi.fasterxml.jackson;

import java.io.IOException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DateDecodeTest {

    @Test
    void go() throws JsonParseException, JsonMappingException, IOException, JSONException {
        String dateStr = "{\"ww\": \"2018-10-12 17:27:51\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject json = new JSONObject(dateStr);
        System.out.println(json.get("ww"));
//        NewClass date = objectMapper.readValue(dateStr, NewClass.class); 抛异常
//        System.out.println(date);
    }
    
    public static class NewClass {
        Date ww;
        public void setWw(Date date) {
            this.ww = date;
        }
    }
}
