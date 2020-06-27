package indi.oracle.java.lang.exception;

import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.util.StringUtils;

import indi.util.extension.TestSeparateExtension;
import net.sf.json.JSONObject;

@ExtendWith(TestSeparateExtension.class)
class ExceptionTest {
    
    /**
     * 抛出异常打印的内容 和 调 printStackTrace方法打印的内容 一致
     * 
     * <p>每次对异常进行封装，都会产生一条新的 `Caused by`
     */
    @Test
    @Disabled
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
    
    /**
     * Java 异常自上而下层层抛出
     */
    @Test
    @Disabled
    void sequenceTest() {
        String a = null;
        a.chars();
    }
    
    void throwNull() throws Exception {
        try {
            JSONObject sfJson = new JSONObject();
            Object object = sfJson.getString("test");
            System.out.println(object);
        } catch (Exception e) {
            try {
                throw new RuntimeException("hhh", e);
            } catch (Exception e1) {
                throw new Exception("test", e1);
            }
        }
        
//        String a = null;
//        a.chars();
    }
    
    /**
     * 测试将异常转化为简要的字符串
     */
    @Test
    void stringifyExcpetionTest() {
        try {
            throwNull();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(stringify(e, 0, 5, "indi"));
        }
    }
    
    /**
     * 
     * 
     * @param e
     * @param depth 记录深度，递归参数，请设为0
     * @param maxDepth 最大记录深度
     * @param keyword 类名关键字
     * @return
     */
    String stringify(Exception e, int depth, int maxDepth, String keyword) {
        Throwable cause = e.getCause();
        String causeStr = null;
        if (cause != null && depth < maxDepth) {
            causeStr = stringify((Exception)cause, depth + 1, maxDepth, keyword);
        }
        return StringUtils.isEmpty(causeStr) ? stringify0(e, keyword) : causeStr + " => " + stringify0(e, keyword);
    }
    
    String stringify0(Exception e, String keyWord) {
        if (e == null) {
            return null;
        }
        String msg = e.getMessage();
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return msg;
        }
        // 取类名（含路径）含关键字的最新一行
        for (StackTraceElement ste : stackTrace) {
            String className = ste.getClassName();
            String fileName = ste.getFileName();
            if (className != null && className.contains(keyWord) 
                    && !"<generated>".equals(fileName)) {// 跳过文件名无法识别的类（主要是代理）。。
                return e.getMessage() + ":" + ste.getFileName() + ":" + ste.getLineNumber();// 这里少打印了完整类路径与方法名
            }
        }
        // 取最后一行
        StackTraceElement ste = stackTrace[0];// 获取栈最外一个元素，即最近一条栈记录
        return e.getMessage() + ":" + ste.getFileName() + ":" + ste.getLineNumber();
    }

}
