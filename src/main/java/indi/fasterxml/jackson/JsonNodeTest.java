package indi.fasterxml.jackson;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class JsonNodeTest {

    /**
     * 如何直接创建json字符串
     */
    @Test
    void go() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("documentinfoid", "123");
//        String text = objectNode.asText(); not this !!
        System.out.println(objectNode.toString());
        System.out.println(objectNode.get("documentinfoid").asText());
        
        
    }
}
