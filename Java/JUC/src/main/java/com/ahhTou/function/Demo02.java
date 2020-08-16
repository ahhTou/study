package com.ahhTou.function;

import java.util.function.Predicate;

public class Demo02 {
    public static void main(String[] args) {
        Predicate<String> fun1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };

        Predicate<String> fun2 = String::isEmpty;

        System.out.println(fun1.test("fun1"));
        System.out.println(fun2.test("fun1"));


    }

}
