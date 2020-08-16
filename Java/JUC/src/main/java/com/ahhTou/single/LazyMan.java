package com.ahhTou.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 懒汉式单例
public class LazyMan {

    // 可以通过反射修改，反射不安全
    private static boolean isCreate = false;

    private LazyMan() {
        synchronized (LazyMan.class) {
            if (isCreate) {
                throw new RuntimeException("不要试图使用反射破坏单例模式Mode1");
            } else {
                isCreate = true;
            }
            if (lazyMan != null) {
                throw new RuntimeException("不要试图使用反射破坏单例模式Mode2");
            }
        }
    }

    private volatile static LazyMan lazyMan;

    // 双重检测说模式的 懒汉式单例 DCL懒汉式
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                    /*
                    1. 分配内存空间
                    2. 执行构造方法，初始化对象
                    3. 把这个对象指向这个空间

                    指令重排
                    132
                    先占用空间，在初始化对象
                    极限情况下
                    导致下一个线程以为对象不为空
                    所以需要 volatile 防止内存重排
                     */
                }
            }
        }
        return lazyMan;
    }

    // 多线程并发
    public static void main(String[] args) throws Exception {
/*        for (int i = 0; i < 10; i++) {
            new Thread(LazyMan::getInstance).start();
        }*/

        /* 破坏单例 */
//        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan lazyMan = declaredConstructor.newInstance();
        LazyMan lazyMan2 = declaredConstructor.newInstance();
//        System.out.println(instance);
        System.out.println(lazyMan);
        System.out.println(lazyMan2);
    }


}
