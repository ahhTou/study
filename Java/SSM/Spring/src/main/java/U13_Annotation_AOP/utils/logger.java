package U13_Annotation_AOP.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，他里面提供了公共代码
 */
@Component("logger")
@Aspect //标识当前类是一个切面类
public class logger {

    @Pointcut("execution(*  U13_Annotation_AOP.service.impl.*.*(..))")
    private void pt1(){

    }

    /**
     * 用于打印日志，计划让其在切入点方法之前执行，切入点方法就是业务层方法
     */
    // 1.前置通知
    @Before("pt1()")
    public void beforePrintLog() {
        System.out.println("-> 前置通知");
    }

    // 2.后置通知
    @AfterReturning("pt1()")
    public void afterReturnPrintLog() {
        System.out.println("-> 后置通知");
    }

    // 3.异常通知
    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("-> 异常通知");
    }

    // 4.最终通知
    @After("pt1()")
    public void afterPrintLog() {
        System.out.println("-> 最终通知");
    }

    // 5.环绕通知

    /**
     * 问题：
     * 当我们配置了环绕通知后，切入点方法没有执行，而通知方法执行了
     * 分析：
     * 通过对比动态代理的环绕通知有明确的切入点方法调用，而我们代码中没有
     * 解决：
     * Spring框架为我们提供了一个接口，ProceedingJoinPoint 该接口有一个方法proceed()。
     * 此方法就相当于明确调用切入点方法
     * 改接口作为环绕通知的方法参入
     * 在程序执行时，spring框架会为我们提供该接口的实现类供我们使用
     *
     * spring中的环绕通知：
     * 它是spring框架为我们]提供的一种可以在代码中
     * @return
     */

    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjb) {
        Object reValue = null;
        try {


            Object[] args = pjb.getArgs();
            System.out.println("--> 前置通知");
            // 方法
            reValue = pjb.proceed(args);
            System.out.println("--> 后置通知");
            return reValue;
        } catch (Throwable throwable) {
            System.out.println("--> 异常通知");


            throw new RuntimeException(throwable);

        } finally {
            System.out.println("--> 最终通知");

        }

    }

}
