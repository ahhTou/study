package U00_基础语法;

import java.util.Random;
import java.util.Scanner;

public class 基础10案例猜数字 {
    public static void main(String[] args) {
        Random r = new Random();
        int number = r.nextInt(100) + 1;

        Scanner sc = new Scanner(System.in);


        System.out.println("请输入你要猜的和数字：");
        while (true) {
            int guessNumber = sc.nextInt();
            if (guessNumber > number) {
                System.out.println("你猜的数字" + guessNumber + "大了");
            } else if (guessNumber < number) {
                System.out.println("你猜的数字" + guessNumber + "小了");
            } else {
                System.out.println("你猜对了");
                break;
            }

        }

    }
}
