package com.ahhTou.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1", "2", "3");
//        list.forEach(System.out::println);


        /*
         *  java.util.ConcurrentModificationException 并发修改异常
         *  解决方案：
         *  new Vector<>(); 比arraylist更早
         */
/*        List<String> list = new ArrayList<>();
        List<String> list = new Vector<>();
        List<String> list = Collections.synchronizedList(new ArrayList<>());*/

        // CopyOnWrite 写入时复制 cow 计算机设计领域的一种优化策略
        // 多个线程调用的时候，list, 读取的时候，固定的，写入(覆盖)
        // 再写入到时候避免覆盖, 造成数据问题
        // 读写分离
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(Thread.currentThread().getName());
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}
