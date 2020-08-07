package U02_方法;

public class demo3 {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        System.out.println(arr[1]);
        change(arr);
        System.out.println(arr[1]);
    }

    // 对于引用类型 的 参数，形式参数的改变，影响实际参数的值
    public static void change(int[] arr) {
        arr[1] = 200;
    }
}
