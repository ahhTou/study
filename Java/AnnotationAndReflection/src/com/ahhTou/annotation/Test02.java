package com.ahhTou.annotation;

import java.lang.annotation.*;

// 测试元注解
public class Test02 {

    @MyAnnotation
    public void test() {

    }

}

// Target 表示我们的注解可以用在那些地方（方法和类）
@Target(value = {ElementType.METHOD, ElementType.TYPE})
// Retention 表示我们的注解在什么地方还有效
@Retention(value = RetentionPolicy.RUNTIME)
// Documented表示是否把我们的注解生成再java doc中、
@Documented
// Inherited 子类可以继承父类的注解
@Inherited
@interface MyAnnotation {

}