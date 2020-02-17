/**
 * 
 */
package indi.spring.jpa;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 测试自定义Spring生成的Repo
 * 
 * @author wzh
 * @since 2020.02.10
 */
public class MyBaseRepoImpl<T, ID> extends SimpleJpaRepository<T, ID> {
    private EntityManager em;
    
    private Class<?> domainClass;
    private Class<?> idClass;

    MyBaseRepoImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        
        // 保存domain信息
        this.domainClass = entityInformation.getJavaType();
        this.idClass = entityInformation.getIdType();

        // Keep the EntityManager around to used from the newly introduced methods.
        this.em = entityManager;
    }
    
    public void printJpaEntityInfo() {
        System.out.println("自定义Repo：");
        System.out.println(domainClass);
        System.out.println(idClass);
    }
}
