package indi.oracle.java.reflect;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import indi.util.PrintUtils;

/**
 * 测试获取当前类所在的路径
 * 
 * @author wzh
 *
 */
class GetAllClassAtSpecificPathTest {

    @ParameterizedTest
    @ValueSource(strings = { "indi/spring/datasource" })
    void test(String location) throws URISyntaxException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(location);
        Path path = Paths.get(url.toURI());
        PrintUtils.print(path);
        if (!Files.isDirectory(path)) {
            PrintUtils.print("not found");
            return;
        }
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
        for (Path p : stream) {
            Path className = p.getFileName();
            
        }
    }

}
