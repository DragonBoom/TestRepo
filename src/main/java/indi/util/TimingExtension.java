package indi.util;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
	private static final Logger logger = LoggerFactory.getLogger(TimingExtension.class);
	private static final String START_TIME = "start_time";

	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		String methodName = context.getRequiredTestMethod().getName();
		long start = getStore(context).remove(START_TIME, long.class);
		long duration = System.currentTimeMillis() - start;
		logger.info("Method {} toke {} ms", methodName, duration);
	}

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		getStore(context).put(START_TIME, System.currentTimeMillis());
	}

	private Store getStore(ExtensionContext context) {
		return context.getStore(Namespace.create(getClass(), context.getRequiredTestClass()));
	}
}
