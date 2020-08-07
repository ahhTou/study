package com.ahhTou.Lamda;

public class TestLambda2 {


    public static void main(String[] args) {
        ILove love = new ILove() {
            @Override
            public void love(int a) {
                System.out.println("I love you -->" + a);
            }
        };

        love = (int a) -> {
            System.out.println("I love you -->" + a);
        };

        love = a -> {
            System.out.println("I love you -->" + a);
        };

        love = a -> System.out.println("I love you -->" + a);

        love.love(2);
        /*
        总结：
            lambda表达式只能有一行代码的情况 下才能简化成为-行，如果有多行，那么就用代码块包裹。
            前提是接口为函数式接口

         */


    }
}

interface ILove {
    void love(int a);
}

