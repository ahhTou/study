package U01_数组;

public class ArrayDemo2 {
    public static void main(String[] args) {
        int[] arr = new int[3];
        arr[0] = 100;
        arr[1] = 200;
        arr[2] = 300;

        System.out.println(arr);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

        int[] arr_2 = arr;
        arr_2[0] = 1;
        arr_2[1] = 2;
        arr_2[2] = 3;
        System.out.println(arr);
        System.out.println(arr_2);
        System.out.println(arr[0]);
        System.out.println(arr_2[0]);


    }
}
