/**
 * 
 */
package indi.spring.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * @author wzh
 * @since 2020.06.23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiDataSource {

    /**
     * 所用数据源的键
     * 
     * @author DragonBoom
     * @since 2020.06.23
     * @return
     */
    @AliasFor("key")
    String value() default "";
    
    String key() default "";
    
    /** 测试用Spring的工具类AnnotationUtils来解析@AliasFor */
    @AliasFor("key")
    String key2() default "";
}
