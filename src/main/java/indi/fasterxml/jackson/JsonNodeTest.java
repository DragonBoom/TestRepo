package indi.fasterxml.jackson;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class JsonNodeTest {

    /**
     * 如何直接创建json字符串
     */
    @Test
    void objectNodeTest() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("documentinfoid", "123");
//        String text = objectNode.asText(); not this !!
        System.out.println(objectNode.toString());
        System.out.println(objectNode.get("documentinfoid").asText());
    }
    
    /**
     * ps. ArrayNode 与 ObjectNode 都是 JsonNode 的子类
     * @throws IOException 
     */
    @Test
    void arrayNodeTest() throws IOException {
        String jsonArray = "[{}, {}]";
        JsonNode jsonNode = new ObjectMapper().readTree(jsonArray);
        System.out.println(jsonNode);// print: [{},{}]
        System.out.println(jsonNode.getClass());// print: class com.fasterxml.jackson.databind.node.ArrayNode
        ArrayNode arrayNode = (ArrayNode) jsonNode;
    }
    
    /**
     * 只能通过ObjectNode或ArrayNode修改json字符串 FIXME: sure?
     * 
     * @throws IOException
     */
    @Test
    void setValueTest() throws IOException {
        String jsonObjectStr = "{\r\n" + 
                "        \"title\": \"核心价值观\",\r\n" + 
                "        \"url\": \"68\",\r\n" + 
                "        \"author\": \"佳佳\",\r\n" + 
                "        \"playVolume\": \"0\",\r\n" + 
                "        \"likeNumber\": \"0\",\r\n" + 
                "        \"score$\": \"0\",\r\n" + 
                "        \"image\": {\r\n" + 
                "            \"id\": 1333,\r\n" + 
                "            \"fileName\": \"timg (3).jpg\",\r\n" + 
                "            \"fileSize\": 40527,\r\n" + 
                "            \"fileExt\": \"jpg\",\r\n" + 
                "            \"contentType\": \"image/jpeg\",\r\n" + 
                "            \"accessKey\": \"402833613\",\r\n" + 
                "            \"md5Code\": \"c51510ee619bb99b1bf77c284a8cc7ca\",\r\n" + 
                "            \"storeType\": \"\",\r\n" + 
                "            \"uploadTime\": \"2019-07-31 15:32:18\"\r\n" + 
                "        },\r\n" + 
                "        \"targetId\": \"68\",\r\n" + 
                "        \"targetName\": \"核心价值观\",\r\n" + 
                "        \"targetType\": \"Course\"\r\n" + 
                "    }";
        JsonNode jsonNode = new ObjectMapper().readTree(jsonObjectStr);
        System.out.println(jsonNode.get("score$"));
        System.out.println(jsonNode.has("score$"));
        // 测试修改jsonNode的core字段的值
        JsonNode scoreNode = jsonNode.get("score$");
        System.out.println(scoreNode);// pring: "0"
        System.out.println(scoreNode.getNodeType());// pring: STRING
        System.out.println(scoreNode.getClass());// print: class com.fasterxml.jackson.databind.node.TextNode
    }
}
