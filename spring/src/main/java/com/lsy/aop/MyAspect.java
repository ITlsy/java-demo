package com.lsy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class MyAspect {

    @Pointcut("execution(* com.lsy.service..*.*(..))")
    public void pt(){

    }
    @Before("pt()")
    public void beforeAdvice(){
        System.out.println("前置通知");
    }
    @AfterReturning(value = "pt()",returning = "result")
    public void afterAdvice(Object result){
        System.out.println("后置通知:"+result);
    }
    @AfterThrowing(value = "pt()",throwing = "e")
    public void exceptionAdvice(Exception e){
        System.out.println("异常通知: "+e.getMessage());
    }
    @After("pt()")
    public void finallyAdvice(){
        System.out.println("最终通知");
    }

    @Around("pt()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("前置通知");
            Object result=joinPoint.proceed();////目标对象方法的执行
            System.out.println("后置通知");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("异常通知");

        }finally {
            System.out.println("最终通知");
        }
    }
}
