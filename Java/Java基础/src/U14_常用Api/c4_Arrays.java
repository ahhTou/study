package U14_常用Api;

import java.util.Arrays;

public class c4_Arrays {
    public static void main(String[] args) {
        int[] arr = {24, 69, 80, 57, 13};
        System.out.println("排序前" + Arrays.toString(arr));

        // 排序
        Arrays.sort(arr);

        System.out.println("排序前" + Arrays.toString(arr));
    }
}
