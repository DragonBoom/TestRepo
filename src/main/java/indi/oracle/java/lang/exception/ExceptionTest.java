package indi.oracle.java.lang.exception;

import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ExceptionTest {
    
    /**
     * 抛出异常打印的内容 和 调 printStackTrace方法打印的内容 一致
     * 
     * <p>每次对异常进行封装，都会产生一条新的 `Caused by`
     */
    @Test
    void hierachyTest() {
        try {
            try {
                throw new SQLException("Runtime Exception");
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable e) {
            e.printStackTrace();// a
            throw new RuntimeException(e);// b
            // a == b
        }
    }

}
