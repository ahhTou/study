package U07_类的继承.c5MethodsRewrite;

public class Phone {
    public void call(String name) {
        System.out.println("给 " + name + " 打电话");
    }

    // 私有无法继承
    private void show() {
        System.out.println("私有信息");
    }

    void say() {
        System.out.println("私有信息");
    }
}
