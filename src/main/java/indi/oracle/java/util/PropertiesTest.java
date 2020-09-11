/**
 * 
 */
package indi.oracle.java.util;

import static org.junit.Assert.assertArrayEquals;

import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import lombok.Cleanup;
import lombok.SneakyThrows;

/**
 * @author wzh
 * @since 2020.09.07
 */
@ExtendWith(TestSeparateExtension.class)
public class PropertiesTest {

    @Test
    @SneakyThrows
    void readPropertiesTest() {
        InputStream inStream = this.getClass().getResourceAsStream("/application-mybatis.properties");

        assert inStream != null;
        
        Properties properties = new Properties();
        properties.load(inStream);
        
        System.out.println(properties);
        
    }
}
