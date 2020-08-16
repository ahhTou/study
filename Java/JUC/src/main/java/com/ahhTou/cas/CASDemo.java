package com.ahhTou.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

public class CASDemo {



    // CAS  compareAndSet 比较并交换
    public static void main(String[] args) {

        // int Integer 如果泛型是一个包装类，注意对象引用问题
        AtomicStampedReference<Integer> atomicInteger
                = new AtomicStampedReference<>(1, 1);

         new ReentrantLock(true);
        /*
         * 期望、更新
         * public final boolean compareAndSet(int expect,int update)
         * 如果我期望的值达到了，那么就更新，否则，就不更新, CAS 是CPU的并发原语。
         *
         * */


        /*
         * ABA
         *
         * */
        new Thread(() -> {

            System.out.println("a1 => " + atomicInteger.getStamp());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicInteger.compareAndSet(1, 2,
                    atomicInteger.getStamp(), atomicInteger.getStamp() + 1));

            System.out.println("a2 => " + atomicInteger.getStamp());

            System.out.println(atomicInteger.compareAndSet(2, 1,
                    atomicInteger.getStamp(), atomicInteger.getStamp() + 1));

        }, "a").start();


        new Thread(() -> {

            int stamp = atomicInteger.getStamp();
            System.out.println("b1 => " + atomicInteger.getStamp());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicInteger.compareAndSet(1, 2,
                    stamp, atomicInteger.getStamp() + 1));

            System.out.println("b1 => " + atomicInteger.getStamp());

        }, "b").start();
    }
}
