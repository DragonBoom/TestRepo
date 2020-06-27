package indi.spring.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaTypeFactory;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class MediaTypeFactoryTest {
    
    @Test
    void parseFileNameTest() {
        String fileName = "a.mp4";
        MediaTypeFactory.getMediaType(fileName)
                .ifPresent(t -> {
                    System.out.println(t.toString());
                });
    }

}
