package U17_文件.c1_File.c5_递归;

public class c1_DiGuiDemo {
    public static void main(String[] args) {
        // 求20个月兔子的对数
        int[] arr = new int[20];

        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println(arr[19]);
        System.out.println(f(20)); //StackOverflowError 堆栈溢出发生时抛出一个应用程序递归太深
    }

    // 递归问题
//    f(n) 表示第n个月的兔子对数

    public static int f(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return f(n - 1) + f(n - 2);
        }
    }

}


