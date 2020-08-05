package com.ahhTou.MyDemo;

import org.junit.Test;

public class MyTest {


    @Test
    public void test() {
        Main main = (Main) MyIOC.getBean();
        main.test();
    }
}
