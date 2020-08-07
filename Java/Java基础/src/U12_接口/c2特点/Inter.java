package U12_接口.c2特点;

public interface Inter {
    //接口中 的 成员变量 默认被 final 、 static、 public 修饰
    public int num = 10;
    public final int num2 = 20;
    public final static int num3 = 30;

//    1. 接口不能有构造方法
//    public Inter() {}
//    2. 接口不能有非抽象方法
//    public void show() {}

    void show();


}
