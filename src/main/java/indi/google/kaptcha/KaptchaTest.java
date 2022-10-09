package indi.google.kaptcha;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

public class KaptchaTest {
    private static String location;

    @BeforeAll
    static void before() {
        location = "e://rua.jpg";
    }

    @Test
    void getKaptcha() throws IOException {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // must have config...
        Properties prop = new Properties();
        // 是否有边框 可选yes 或者 no
        prop.setProperty("kaptcha.border", "yes");

        defaultKaptcha.setConfig(new Config(prop));
        BufferedImage image = defaultKaptcha.createImage("RUA");
        
        ImageIO.write(image, "jpg", new File(location));
    }

    @AfterEach
    void after() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Press Anything To Delete Img ...");
        scan.nextLine();
        scan.close();
        Path p = Paths.get(location);
        if (Files.exists(p))
            Files.delete(Paths.get(location));
        System.out.println("Delete Complete");
    }
}
