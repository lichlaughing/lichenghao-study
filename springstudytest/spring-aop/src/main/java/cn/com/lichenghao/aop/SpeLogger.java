package cn.com.lichenghao.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("speLogger")
@Aspect
public class SpeLogger {

    @Pointcut(value = "execution(* cn.com.lichenghao.service.impl.*.*(..))")
    private void pt() {

    }

    @Before("pt()")
    public void beforeOrgLogger() {
        System.out.println("前置通知 Executed !");
    }

    @AfterReturning("pt()")
    public void afterReturnOrgLogger() {
        System.out.println("后置通知 Executed !");
    }

    @AfterThrowing("pt()")
    public void throwOrgLogger() {
        System.out.println("异常通知 Executed !");
    }

    @After("pt()")
    public void afterOrgLogger() {
        System.out.println("最终通知 Executed !");
    }

    //@Around("pt()")
    public Object aroundOrgLogger(ProceedingJoinPoint proceedingJoinPoint) {
        Object data = null;
        try {
            System.out.println("前置通知");
            Object[] args = proceedingJoinPoint.getArgs();
            data = proceedingJoinPoint.proceed(args);
            System.out.println("后置通知");
        } catch (Throwable e) {
            System.out.println("异常通知");
        } finally {
            System.out.println("最终通知");
        }
        return data;
    }
}
