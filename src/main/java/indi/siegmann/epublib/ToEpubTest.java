package indi.siegmann.epublib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

/**
 * 测试epub的工具库 ?
 * 
 * @author wzh
 * @since 2020.02.04
 */
@ExtendWith(TestSeparateExtension.class)
public class ToEpubTest {

    @Test
    void go() throws IOException {
        Book book = new Book();
        byte[] bytes = "<html lang=\"en\">wahahahha </html>".getBytes();
        MediaType mediaType = MediatypeService.XHTML;
        book.addSection("wahaha", new Resource(bytes, mediaType));
        
        EpubWriter epubWriter = new EpubWriter();
        OutputStream newOutputStream = Files.newOutputStream(Paths.get("d://", "1.epub"),
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        epubWriter.write(book, newOutputStream);

    }

}
