package U06_列表;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();

        // 1. array.add(index, value) 添加
        System.out.println(array.add("hello"));
        array.add("world");
        array.add("ohhh~");
        array.add(1, "javaSe");

        System.out.println("array" + array);

        // 2. array.remove(index/Object o) 移除
        array.remove("world");// 返回Boolean值
        System.out.println("array" + array);
        array.remove(0);// 返回Boolean值
        System.out.println("array" + array);

        // 3. array.set(index, e) 修改
        array.set(1, "javaEE"); //返回被修改的元素
        System.out.println("array" + array);

        // 4. array.get(index) 返回指定索引
        System.out.println("get:" + array.get(0));

        // 5. array.size() 返回集合中元素的个数
        System.out.println("size:" + array.size());

    }
}
