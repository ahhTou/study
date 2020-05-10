package U23_函数式接口.c1_概述;

public class MyInterfaceDemo {
    public static void main(String[] args) {

        // 有且仅有一个抽象方法的接口
        MyInterface my = () -> System.out.println("函数式接口");

        my.show();
    }
}
