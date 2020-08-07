package U00_基础语法;

import java.util.Random;

public class 基础08Random {
    public static void main(String[] args) {
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            int number = r.nextInt(10) + 1; // 获取数据的范围0-10 包括零不包括1

            System.out.println("输出0-10 的随机数 = " + number);
        }
    }
}
