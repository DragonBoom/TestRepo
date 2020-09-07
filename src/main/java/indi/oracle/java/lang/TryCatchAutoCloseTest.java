package indi.oracle.java.lang;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class TryCatchAutoCloseTest {

    /**
     * 测试 try-with-resources 语句
     * 
     * @throws IOException
     */
    @Test
    void go() throws IOException {
        try (MyByteArrayStream inputStream = new MyByteArrayStream("hello".getBytes())) {
            System.out.println(inputStream);
        } // print [procee close()]
    }

    private class MyByteArrayStream extends ByteArrayInputStream {

        public MyByteArrayStream(byte[] buf) {
            super(buf);
        }

        @Override
        public void close() {
            System.out.println("procee close()");
        }
    }
}
