package indi.oracle.java.lang.reflect;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import indi.util.extension.TestSeparateExtension;

/**
 * 测试获取当前类所在的路径
 * 
 * @author wzh
 *
 */
@ExtendWith(TestSeparateExtension.class)
class GetAllClassAtSpecificPathTest {

    @ParameterizedTest
    @ValueSource(strings = { "indi/spring/datasource" })
    void test(String location) throws URISyntaxException, IOException, ClassNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(location);
        Path path = Paths.get(url.toURI());
        System.out.println(path);
        if (!Files.isDirectory(path)) {
            System.out.println("not found");
            return;
        }
        System.out.println();
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
        for (Path p : stream) {
            System.out.println(p);
            // E:\github\TestRepo\target\classes\indi\spring\datasource\Application.class -> 
            // indi\springdatasource\Application.class
            int count = p.getNameCount();
            int aimIndex = count;
            for (int i = 0; i< count; i++) {
                Path name = p.getName(i);
                if (name.toString().equals("classes")) {
                    aimIndex = i + 1;
                    break;
                }
            }
            // indi\springdatasource\Application.class -> 
            // indi\springdatasource\Application
            Path subpath = p.subpath(aimIndex, p.getNameCount());
            // indi.springdatasource.Application -> indi.springdatasource.Application

            String fileName = subpath.toString().replace('\\', '.');
            String className = fileName.substring(0, fileName.lastIndexOf('.'));
            System.out.println(className);
            Class.forName(className.toString());
            System.out.println("===========");
        }
    }

}
