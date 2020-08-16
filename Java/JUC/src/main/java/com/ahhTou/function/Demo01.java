package com.ahhTou.function;

import java.util.function.Function;

/*
    Function函数型接口，有一个输入参数，有一个输出
    只要是函数型接口可以用lambda表达式简化
 */
public class Demo01 {
    public static void main(String[] args) {
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };

        Function function1 = str -> str;

        System.out.println(function.apply("啦啦啦"));
        System.out.println(function1.apply("呜呜呜"));
    }
}
