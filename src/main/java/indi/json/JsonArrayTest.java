package indi.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

public class JsonArrayTest {

    @Test
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
}
