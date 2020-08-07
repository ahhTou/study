package U21_接口组成更新.c2_接口中的静态方法;

public interface Inter {
    void show();

    default void method(){
        System.out.println("Inter 中的 默认方法");
    }

    // 只能被接口调用
    static void test(){
        System.out.println("Inter 中的 静态方法");
    }


}
