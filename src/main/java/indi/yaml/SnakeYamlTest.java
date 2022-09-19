/**
 * 
 */
package indi.yaml;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

/**
 * 
 * 
 * @author wzh
 * @since 2022.01.02
 * @see indi.fasterxml.jackson.YamlTest
 */
@ExtendWith(TestSeparateExtension.class)
class SnakeYamlTest {

    // 尝试第一次读取yml文件（测试发现可以解析yml中的锚与别名）
    // 发现SnakeYaml默认不保留注释...
    // 发现SnakeYaml似乎无法只通过配置就自动输出锚
    // 发现最新版支持将Node转化为String（即 Yaml.serialize(Node node, Writer output)）
    // SnakeYaml要实现输出锚，根据 BaseRepresenter 的源码，需要将锚对应的键或值改用同一个对象（而不能只是内容相等），因为
    // 其用IdentityHashMap进行缓存
    // Node 的结构可见 Serializer 的 anchorNode(Node)，里面有对Node的遍历
    @Test
    @SneakyThrows
    void go() {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
        Yaml yaml = new Yaml(dumperOptions);
        ClassPathResource resource = new ClassPathResource("test//asset.yml");
        Map<?, ?> map = yaml.load(Files.newBufferedReader(Paths.get(resource.getURI())));
        renewYamlMap(map);
        System.out.println(yaml.dump(map));
    }
    
    void renewYamlMap(Map<?, ?> map) {
        Map<?, ?> images = (Map<?, ?>) map.get("sprite");
        boolean first = true;
        Map<?, ?> rect = null;
        Map<?, ?> pivot = null;
        Map<?, ?> border = null;
        for (String key : (Set<String>) images.keySet()) {
            Map<String, Object> image = (Map<String, Object>)images.get(key);
            if (first) {
                rect = (Map<?, ?>)image.get("rect");
                pivot = (Map<?, ?>) image.get("pivot");
                border = (Map<?, ?>) image.get("border");
                first = false;
            } else {
                image.put("rect", rect);
                image.put("pivot", pivot);
                image.put("border", border);
            }
        }
    }
}
