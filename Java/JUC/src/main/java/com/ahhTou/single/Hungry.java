package com.ahhTou.single;

// 饿汉式单例
public class Hungry {


    private Hungry() {

    }

    private final static Hungry HUNGRY = new Hungry();

    private static Hungry getInstance() {
        return HUNGRY;
    }

}
