/**
 * 
 */
package indi.apache.poi;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

/**
 * https://poi.apache.org/components/document/
 * 
 * @author wzh
 * @since 2020.03.03
 */
@ExtendWith(TestSeparateExtension.class)
class WordTest {

    /**
     * 测试由文本创建word文件。官网没有详细的教程，自己摸索算了。
     * 
     * 
     * @author DragonBoom
     * @throws IOException
     * @since 2020.03.03
     */
    @Test
    void createWrodByTextTest() throws IOException {
        // 创建doc对象
        try (XWPFDocument doc = new XWPFDocument()) {// implements Document
            XWPFParagraph paragraph = doc.createParagraph();// 新建“段落”

            XWPFRun run = paragraph.createRun();// 为段落新建一“行”
            run.addCarriageReturn();// 回车
            run.setText("test111111111111111111111111111111111111");

            // 写入至磁盘文件
            OutputStream outputStream = Files.newOutputStream(Paths.get("e:", "test.docx"), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            doc.write(outputStream);

        }

    }

}
