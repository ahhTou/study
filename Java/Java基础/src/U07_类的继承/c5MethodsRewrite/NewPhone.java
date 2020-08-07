package U07_类的继承.c5MethodsRewrite;

// 只能继承一个类 ，但是可以多层继承

public class NewPhone extends Phone {

    // 重写标识
    @Override
    public void call(String name) {
        System.out.println("开启视频功能");
        super.call(name);
    }
    // 私有无法继承
//    @Override
//    public void show(){
//
//    }

    @Override
    void say() {
        System.out.println("私有信息");
    }

}
