package indi.spring.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class MediaTypeTest {
    
    @Test
    void getTypeTest() {
        // .mp3 的mediaType 为 audio/mpeg，而不是audio/mp3（浏览器一样能播放）
        MediaTypeFactory.getMediaType("aaa.png").map(MediaType::toString).ifPresent(System.out::println);
    }
}
