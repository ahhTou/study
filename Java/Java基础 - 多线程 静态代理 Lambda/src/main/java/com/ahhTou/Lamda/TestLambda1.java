package com.ahhTou.Lamda;

/*
    推到lambda表达式
 */
public class TestLambda1 {

    // 3.静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("I like lambda2");
        }
    }

    public static void main(String[] args) {
        // 4.局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("I like lambda3");
            }
        }
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        like = new Like3();
        like.lambda();

        // 5. 匿名内部类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I like lambda4");
            }
        };

        like.lambda();

        // 6. 用lambda简化
        like = () -> {
            System.out.println("I like lambda5");
        };
        like = () -> System.out.println("I like lambda6");
    }

}

// 1. 定义一个函数时接口
interface ILike {
    void lambda();
}

// 2. 实现类
class Like implements ILike {
    @Override
    public void lambda() {
        System.out.println("I like lambda");
    }
}
