package com.ahhTou.reflection;

public class Test06 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获得系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取系统类加载器的父加载器 -> 扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        // 获取扩展类加载器的父类加载器 -> 根加载器（c/c++）
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        // 测试当前类是哪个加载器加载的
        ClassLoader classLoader = Class.forName("com.ahhTou.reflection.Test06").getClassLoader();
        System.out.println(classLoader); // 系统类加载器

        // 测试Jkd内置的列是谁加载的
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader); // 根加载器

        // 如何获得系统类加载器可以加载的的路径
        System.out.println(System.getProperty("java.class.path"));

        // 双亲委派机制
            // 保证安全性，加入上层有相同的类，类加载器会加载上层的类，而不会加载自己写的

        /*
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\charsets.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\deploy.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\access-bridge-64.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\cldrdata.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\dnsns.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\jaccess.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\jfxrt.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\localedata.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\nashorn.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\sunec.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\sunjce_provider.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\sunmscapi.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\sunpkcs11.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\ext\zipfs.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\javaws.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\jce.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\jfr.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\jfxswt.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\jsse.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\management-agent.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\plugin.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\resources.jar;
        C:\Program Files\Java\jdk1.8.0_74\jre\lib\rt.jar;
        D:\PrivateFiles\_study\learn\study\Java\AnnotationAndReflection\out\production\AnnotationAndReflection;
        D:\Program Files\JetBrains\IntelliJ IDEA 2020.1\lib\idea_rt.jar

         */

    }
}
