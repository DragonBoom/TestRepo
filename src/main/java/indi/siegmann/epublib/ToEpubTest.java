package indi.siegmann.epublib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import nl.siegmann.epublib.domain.Book;

@ExtendWith(TestSeparateExtension.class)
public class ToEpubTest {
    
    @Test
    void go() {
        Book book = new Book();
    }

}
