package com.ahhTou.MyDemo;

import org.junit.Test;

public class Main {

    @MyAnnotation
    String v1;
    @MyAnnotation
    String v2;
    @MyAnnotation
    String v3;
    @MyAnnotation
    String v4;

    @Test
    public void test() {
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
    }

}
