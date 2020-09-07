package indi.spring.data.jpa;

import org.springframework.stereotype.Component;

@Component
public class IndividualQueryDaoImpl implements IndividualQueryDao {

    /**
     * 测试用的独立接口；通过在JPA接口上继承（接口可多继承）该类的接口，可为JPA接口添加该实现方法
     * 
     */
    @Override
    public void go() {
        System.out.println("go!!!");
    }

}
