package U21_接口组成更新.c1_接口中的默认方法;

public interface MyInterface {
    void show1();
    void show2();

    default void show3(){
        System.out.println("show3");
    }
}
