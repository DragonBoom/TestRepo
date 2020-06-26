package indi.coobird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@ExtendWith(TestSeparateExtension.class)
class ThumbnaliatorTest {
    File testJpg = new File("e:/test/test.jpg");
    File testJpg2 = new File("e:/test/test2.jpg");
    File testPng = new File("e:/test/test.png");
    File testPng2 = new File("e:/test/test2.png");
    File tmpFile = new File("e:/test/tmp.dat");
    
    @Test
    @Disabled
    void getImageInfo() throws IOException {
        BufferedImage image = Thumbnails.of(testPng).scale(1).asBufferedImage();
        // 获取图片高度
        System.out.println("width: " + image.getWidth());
        System.out.println("height: " + image.getHeight());
        // 获取图片类型
        int type = image.getType();
        System.out.println(type);
        
    }
    
    @Test
    @Disabled
    void compressTest() throws IOException {
        // 通过指定高度压缩
        // 处理半透明png，透明部分会变黑
        Thumbnails.of(testJpg).height(1024).allowOverwrite(true)
        /*
         * 指定图片类型，以解决处理半透明png变色问题，该配置项默认为原图类型
         *  
         */
                .imageType(BufferedImage.TYPE_INT_ARGB)// 指定图片类型，从而处理png透明变色问题，默认为 BufferedImage.TYPE_INT_ARGB;
//                 .toFile(tmpFile);// 默认覆盖指定文件
                .toFile(testJpg2);// 默认覆盖指定文件
    }
    
    @Test
    @Disabled
    void paramTest() {
//        ThumbnailParameter.
//        Thumbnails.of(testPng).
    }
    
    @Test
    @Disabled
    void qualityTestForJpg() throws IOException {
        // 测试降低图片质量，图片大小是否会发生变化
        // jpg 会变化，但png不会
        long len = testJpg.length();
        Thumbnails.of(testJpg).scale(1).outputQuality(0.1f).toFile(testJpg2);
        long len2 = testJpg2.length();
        System.out.println(len);
        System.out.println(len2);
        System.out.println(len == len2);// println false
    }
    
    @Test
    void qualityTestForPng() throws IOException {
        // 测试降低图片质量，图片大小是否会发生变化
        // jpg 会变化，但png不会
        long len = testPng.length();
        Thumbnails.of(testPng)
//                .imageType(BufferedImage.TYPE_INT_ARGB)// 3750304
                .scale(1)
                .toFile(testPng2);
        long len2 = testPng2.length();
        System.out.println(len);
        System.out.println(len2);
        System.out.println(len == len2);// println false
    }
    
    @Test
    @Disabled
    void cropTest() throws IOException {
        Thumbnails.of(testJpg).size(640, 480).crop(Positions.CENTER).toFile(testJpg2);
    }

}
