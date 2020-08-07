package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c2_Consumer;

import java.util.function.Consumer;

public class c2_练习 {
    public static void main(String[] args) {
        String[] strArray = {"ahhTou,20", "Galaxy,21", "FireEdge,19"};

        printInfo(strArray, str -> {
            String name = str.split(",")[0];
            System.out.print("姓名：" + name + ",");

        }, str -> {
            String age = str.split(",")[1];
            System.out.println("年龄为：" + age);
        });


    }

    private static void printInfo(
            String[] strArray,
            Consumer<String> con1,
            Consumer<String> con2
    ) {
        for (String str : strArray) {
            con1.andThen(con2).accept(str);
        }
    }
}
