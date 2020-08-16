package com.ahhTou.function;

import java.util.function.Supplier;

public class Demo04 {
    public static void main(String[] args) {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };


        Supplier<Integer> supplier1 = () -> 999;

        System.out.println(supplier.get());
        System.out.println(supplier1.get());
    }
}
