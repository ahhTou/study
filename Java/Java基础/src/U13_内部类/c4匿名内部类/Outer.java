package U13_内部类.c4匿名内部类;

// 本质是 继承 或者 实现了该接口的之类匿名对象

public class Outer {
    public void method() {

        Inter i = new Inter() {
            @Override
            public void show() {
                System.out.println("匿名内部类");
            }
        };
        i.show();

    }
}
