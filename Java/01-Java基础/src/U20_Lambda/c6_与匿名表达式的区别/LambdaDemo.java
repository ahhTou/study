package U20_Lambda.c6_与匿名表达式的区别;

public class LambdaDemo {
    public static void main(String[] args) {

        // 1.匿名内部类 ,会生成单独的class 字节码文件
        useInter(new Inter() {
            @Override
            public void show() {
                System.out.println("接口");
            }
        });

        useAnimal(new Animal() {
            @Override
            public void method() {
                System.out.println("抽象类");
            }
        });

        useStudent(new Student() {
            @Override
            public void study() {
                System.out.println("具体类");
            }
        });

        System.out.println("=====");
        // 2. lambda
        useInter(() -> System.out.println("接口"));
//        useAnimal(() -> System.out.println("接口")); //必须是接口
//        useStudent(() -> System.out.println("接口")); //必须是接口


    }

    private static void useStudent(Student s) {
        s.study();
    }

    private static void useAnimal(Animal a) {
        a.method();
    }

    private static void useInter(Inter i) {
        i.show();
    }
}
