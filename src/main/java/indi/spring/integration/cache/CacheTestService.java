/**
 * 
 */
package indi.spring.integration.cache;

/**
 * @author wzh
 * @since 2020.02.17
 */
public interface CacheTestService {
    
    String setConfName(String p1, String v1);
    
    String getConfName(String p1);
    
    void cleanConfName(String p1);
    
    /**
     * 缓存无关地修改指定值，以此测试缓存注解是否有发挥作用：若有，则用该方法修改值后，通过缓存方法获取得到的仍是旧的值
     * 
     * @author DragonBoom
     * @since 2020.02.17
     * @param p1
     * @param v1
     */
    void setConfNameNoCache(String p1, String v1);
}
