/**
 * 
 */
package indi.oracle.java.awt.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import indi.util.CheckUtils;

/**
 * @author wzh
 * @since 2020.09.29
 */
@ExtendWith(TestSeparateExtension.class)
class BufferedImageTest {
    
    /**
     * 参考自：https://www.tutorialspoint.com/how-to-compare-two-images-using-java-opencv-library
     * 
     * <p>通过比较像素点RGB值的差异比较图片
     * 
     * @throws IOException
     * @since 2020.09.29
     */
    @Test
    void compareTest() throws IOException {
        Path pic = Paths.get("e:/test1.png");
        Path pic2 = Paths.get("e:/test2.png");
        boolean checkCrc32 = false;
        
        // 先简单地校验crc32
        if (checkCrc32) {// 只需2s
            if (CheckUtils.getCRC32(pic).equals(CheckUtils.getCRC32(pic2))) {
                return;
            }
        }
        // < 1s
        BufferedImage image1 = ImageIO.read(pic.toFile());
        BufferedImage image2 = ImageIO.read(pic2.toFile());
        
        int h1 = image1.getHeight();
        int w1 = image1.getWidth();
        
        int h2 = image2.getHeight();
        int w2 = image2.getWidth();
        
        int minH = Math.min(h1, h2);
        int minW = Math.min(w1, w2);
        
        int scale = 4;
        BigDecimal result = BigDecimal.valueOf(0).setScale(scale);
        
        int max = 255;
        BigDecimal maxBD = BigDecimal.valueOf(max);
       
        int count = 0;
        for (int h = 0; h < minH; h++) {
            for (int w = 0; w < minW; w++) {
                count++;
                int rgb1 = image1.getRGB(w, h);
                Color color1 = new Color(rgb1);

                int rgb2 = image2.getRGB(w, h);
                Color color2 = new Color(rgb2);
                
                int blue = Math.abs(color1.getBlue() - color2.getBlue());// <= 255
                int red = Math.abs(color1.getRed() - color2.getRed());
                int green = Math.abs(color1.getGreen() - color2.getGreen());
                
                
                result = calcDifferValue1(blue, red, green);
            }
        }
        result = result.divide(BigDecimal.valueOf(count), scale, RoundingMode.HALF_UP);
        System.out.println(result);
    }
    
    /**
     * 比较像素点
     * 
     * <p>只比较像素点是否相同
     * 
     * @param blue
     * @param red
     * @param green
     * @return 返回值在0到1之间；0表示完全相同
     * @since 2020.10.01
     */
    private BigDecimal calcDifferValue1(int blue, int red, int green) {
        if (blue == 0 && red == 0 && green == 0) {
            return BigDecimal.valueOf(0);
        } else {
            return BigDecimal.valueOf(1);
        }
    }
    
    /**
     * 求像素点的平均差
     * 
     * @param blue
     * @param red
     * @param green
     * @return
     * @since 2020.10.01
     */
    private BigDecimal calcDifferValue2(int blue, int red, int green) {
        int max = 255;
        BigDecimal maxBD = BigDecimal.valueOf(max);
        int scale = 4;
        return BigDecimal.valueOf(blue).divide(maxBD, scale, RoundingMode.HALF_UP)
            .add(BigDecimal.valueOf(red).divide(maxBD, scale, RoundingMode.HALF_UP))
            .add(BigDecimal.valueOf(green).divide(maxBD, scale, RoundingMode.HALF_UP))
            .divide(BigDecimal.valueOf(3), scale, RoundingMode.HALF_UP);
    }

}
