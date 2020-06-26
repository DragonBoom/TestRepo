/**
 * 
 */
package indi.spring.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 拦截自定义注解的切面，实现根据注解参数切换数据源、从而实现分表分库的效果
 * 
 * @author wzh
 * @since 2020.06.23
 */
@Aspect
@Component
@Slf4j
// @Order 值越小优先级越高
// 重要：确保该切面比@Transactional更早执行，避免出现已获取连接再修改数据源键的情况
@Order(-1)
public class TestMultiDataSourceAspect {
    

    public TestMultiDataSourceAspect() {
        super();
        log.info("init TestMultiDataSourceAspect");
    }

    // &&后面一节应该用于指定参数
    @Before("@annotation(indi.spring.datasource.MultiDataSource)&&@annotation(multiDataSource)")
    public void beforeTransaction(JoinPoint jp, MultiDataSource multiDataSource) {
        log.debug("Enter JoinPoint: {}", jp);
        // 获取默认值，可解析@AliasFor FIXME: 测试中 N 至少getValue方法不行，但应该有其他方法可以实现这个效果
        String key = (String) AnnotationUtils.getValue(multiDataSource, "key2");
        log.debug("key = {}", key);
        DynamicDataSource.setLookupKey(key);
    }
}
