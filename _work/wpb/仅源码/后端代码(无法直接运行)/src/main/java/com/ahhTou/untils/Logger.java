package com.ahhTou.untils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("Logger")
@Aspect
public class Logger {

    @Pointcut("execution(*  com.ahhTou.service.impl.*.*(..))")
    private void pt1() {
    }

    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjb) {
        Object reValue = null;
        try {

            Object[] args = pjb.getArgs();
            System.out.println("  > 开始执行Service中的方法");
            // 方法
            reValue = pjb.proceed(args);
            System.out.println("  > 执行Service中的方法成功");
            return reValue;

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("  > 执行Service中的方法出现异常");
            throw new RuntimeException(throwable);

        } finally {
            System.out.println("  > 执行Service中的方法结束");
        }

    }
}
