/**
 * 
 */
package indi.spring.datasource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wzh
 * @since 2020.06.23
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    protected static ThreadLocal<String> lookupKeys = new ThreadLocal<String>();

    @Override
    protected Object determineCurrentLookupKey() {
        String key = lookupKeys.get();
        if (StringUtils.isEmpty(key)) {
            log.debug("当前线程未注册数据源的键");
            // 这里不应该抛异常，因为可以返回null让父类通过其他方式获取数据源
//            throw new RuntimeException("无法获取数据源源的LookupKey");
        } else {
            log.debug("获取到数据源的键{}", key);
        }
        return key;
    }
    
    /**
     * 为当前线程设置线程池的LookupKey。该方法需要在事务前执行！
     * 
     * <p>不需要考虑key的回收问题，key最终是存放在线程上的，线程结束时就会被回收
     * 
     * @author DragonBoom
     * @since 2020.06.23
     * @param key
     * @return
     */
    public static Object setLookupKey(String key) {
        // key  check
        lookupKeys.set(key);
        return key;
    }

}
