package com.ahhtou01.array;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] arr = new int[3];
        // int 说明元素类型是int类型
        // [] 说明是一个数组
        // arr 数组的名称

        // new 为数组申请空间
        // [] 说明是一个数组
        // 3 数组元素个数

        // 输出数组名
        System.out.println(arr);

        // 输出数组中的元素
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        // int 默认 0
        // 浮点数 默认 0.0
        //

        // 栈内存 ： 储存局部变量 ，使用完毕会立即消失
        // 堆内存 ： 存储new出来的内容（实体，对象）,会在垃圾回收器空闲时回收
    }
}
