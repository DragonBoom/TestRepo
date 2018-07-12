package indi.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationTest {
	private static final Logger logger = LoggerFactory.getLogger("");

	/**
	 * 利用反射获取注解
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	void testMain() throws Exception {
		logger.info("当前类路径(class path?) {}", this.getClass().getResource("/").getPath());;
		String className = "indi.annotation.GetTest";
		Class<?> c = Class.forName(className);
		Object o = c.newInstance();
		// 先扫描方法
		for (Method m : c.getMethods()) {
			logger.info("{}'s method :{} with annotation {}", c.getName(), m.getName(),
					m.getAnnotations());
			Register g = null;
			if ((g = m.getAnnotation(Register.class)) != null) {
				String name = g.value();
				if (!name.equals("")) {
					// 相当于注册bean
					addObject(name, m.invoke(o));
					logger.info("method name: {}", g.value());
				} else {
					addObject(m.getName(), m.invoke(o));
					logger.info("method name: {}", m.getName());
				}
			}
		}
		// 再扫描值域（成员变量），进行装配
		for (Field f : c.getDeclaredFields()) {
			Get g = null;
			if ((g = f.getAnnotation(Get.class)) != null) {
				String name = g.value();
				if (!name.equals("")) {
					f.set(o, findObject(g.value()));
					logger.info("final ,field {}: {}", f.getName(),
							o.getClass().getField(f.getName()).get(o));
				} else {
					f.set(o, findObject(f.getName()));
					logger.info("final ,field {}: {}", f.getName(),
							o.getClass().getField(f.getName()).get(o));
				}
			}
		}
	}

	// bean名，对象 映射容器
	private static final HashMap<String, Object> objectMap = new HashMap<String, Object>();

	Object findObject(String objectName) {
		Object o = null;
		if ((o = objectMap.get(objectName)) == null)
			return null;
		else
			return o;
	}

	void addObject(String objectName, Object o) {
		logger.info("add {} {} with class {}", objectName, o, o.getClass());
		objectMap.put(objectName, o);
	}

}
