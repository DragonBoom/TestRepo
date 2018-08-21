package indi.spring.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* indi.spring.core.aop.*.*(..))")
    public void test() {
        System.out.println("test");
    }
    
    @Before("test()")
    public void doBefore(JoinPoint point) {
        System.out.println("before");
    }
}
