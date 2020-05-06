package U13_内部类.c5匿名内部类在开发中的使用;

public class JumppingDemo {
    public static void main(String[] args) {
        // 需求:创建接口操作类的对象 调用method方法
        JumppingOperator jo = new JumppingOperator();
        Jumpping j = new Cat();
        jo.method(j);

        Jumpping j2 = new Cat();
        jo.method(j2);
        System.out.println("======");

        jo.method(new Jumpping() {
            @Override
            public void jump() {
                System.out.println("猫可以跳高了");
            }
        });
    }
}
