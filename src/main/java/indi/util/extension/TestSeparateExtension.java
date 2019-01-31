package indi.util.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestSeparateExtension
        implements BeforeTestExecutionCallback, AfterTestExecutionCallback, BeforeAllCallback, AfterAllCallback {
    private static final String SEPARATOR = "---------------------------------";
    private static final String BEGIN_ALL = "begin";
    private static final String AFTER_ALL = "over";

    @Override
    public void beforeTestExecution(ExtensionContext ctx) throws Exception {
        System.out.println(SEPARATOR);

    }

    @Override
    public void afterTestExecution(ExtensionContext ctx) throws Exception {
        System.out.println(SEPARATOR);
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
