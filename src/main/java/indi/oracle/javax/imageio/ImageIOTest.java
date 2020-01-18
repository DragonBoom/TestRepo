package indi.oracle.javax.imageio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ImageIOTest {
    
    @Test
//    @Disabled
    void suffixesTest() {
        // 获取支持的图片文件后缀：
        System.out.println(Arrays.asList(ImageIO.getReaderFileSuffixes()));// print: [jpg, bmp, gif, png, jpeg, wbmp]
    }
    
    @Test
    @Disabled
    void getImageTest() throws IOException {
        // 测试后缀不是图片格式，能否读取图片
        BufferedImage image = ImageIO.read(new File("e://素材/test.png.dat"));
        System.out.println(image);
    }

}
