/**
 * 
 */
package indi.fasterxml.jackson;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import lombok.SneakyThrows;

/**
 * 
 * 
 * @author wzh
 * @since 2022.01.04
 * @see indi.yaml.SnakeYamlTest 
 */
public class YamlTest {

    /**
     * 
     * 似乎不支持锚。。。
     * 
     * @since 2022.01.04
     */
    @Test
    @SneakyThrows
    void go() {
        ClassPathResource resource = new ClassPathResource("test//asset.yml");
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String yamlStr = new String(bytes);
        YAMLFactory factory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<HashMap<?, ?>> type = new TypeReference<HashMap<?, ?>>() {};
        Object map = mapper.readValue(bytes, type);
        yamlStr = mapper.writeValueAsString(map);
        System.out.println(yamlStr);
    }
}
