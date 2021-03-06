package indi.util.extension;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * 引入该插件以为每个单元测试添加分隔符，以及其他优化处理
 * 
 * @author DragonBoom
 *
 */
public class TestSeparateExtension
        implements BeforeTestExecutionCallback, AfterTestExecutionCallback, BeforeAllCallback, AfterAllCallback {
    private static final String BEGIN_SEPARATOR = "↓↓↓---------------------------------↓↓↓";
    private static final String OVER_SEPARATOR = "↑↑↑---------------------------------↑↑↑";
    private static final String BEGIN_ALL = "begin";
    private static final String AFTER_ALL = "over";
    private ThreadLocal<Date> beginDateThreadLocal = new ThreadLocal<>();

    @Override
    public void beforeTestExecution(ExtensionContext ctx) throws Exception {
        beginDateThreadLocal.set(new Date());
  
        String displayName = ctx.getDisplayName();
        Class<?> requiredTestClass = ctx.getRequiredTestClass();
        
        System.out.println(new StringBuilder(BEGIN_SEPARATOR)
                .append("Begin Test: ")
                .append(requiredTestClass.getSimpleName()).append(".").append(displayName)
                .toString());
    }

    @Override
    public void afterTestExecution(ExtensionContext ctx) throws Exception {
        Date now = new Date();
        Date beginDate = beginDateThreadLocal.get();
        // 计算花费时间
        long duration = now.getTime() - beginDate.getTime();
        String timeDesc = null;
        if (duration > 1000) {
            timeDesc = new StringBuilder().append(duration/1000).append(" s").toString();
        } else {
            timeDesc = new StringBuilder().append(duration).append(" millis").toString();
        }
        System.out.println(new StringBuilder(OVER_SEPARATOR).append(" Test Over, Use ").append(timeDesc).toString());
        System.out.println();// 空一行
        
        // 打印异常堆记录，从而不必进到JUnit窗口才能查看异常信息
        ctx.getExecutionException().ifPresent(Throwable::printStackTrace);
    }
    
    @Override
    public void beforeAll(ExtensionContext ctx) throws Exception {
        System.out.println(BEGIN_ALL);
    }

    @Override
    public void afterAll(ExtensionContext arg0) throws Exception {
        System.out.println(AFTER_ALL);
    }


}
