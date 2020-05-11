package U23_函数式接口_Lambda扩展.c4_常见的函数式接口.c1_Supplier;

import java.util.function.Supplier;

public class c2_练习 {
    public static void main(String[] args) {

        // 定义一个数组
        int[] arr = {19, 50, 28, 37, 46};

        int maxValue = getMax(() -> {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            return max;
        });

        System.out.println(maxValue);
    }

    private static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }
}
