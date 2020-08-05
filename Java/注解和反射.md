# 注解

## Test01 内置注解

```java
// 什么是注解 （不是必须，但是有些程序存在）
public class Test01 {

    // @Override 重写的注解 （检查和约束）
    @Override
    public String toString() {

        return "Test01{}";

    }

    // 已过时的，但是可以使用，但是存在更好的方法
    @Deprecated
    public static void test() {
        System.out.println("Deprecated");
    }

    // 镇压警告， 可以放在类和方法上面
    @SuppressWarnings("all")
    public void test02() {
        List list = new ArrayList();
    }
}
```

## Test02 元注解

```java
// Target 表示我们的注解可以用在那些地方（方法和类）
@Target(value = {ElementType.METHOD, ElementType.TYPE})
// Retention 表示我们的注解在什么地方还有效
@Retention(value = RetentionPolicy.RUNTIME)
// Documented表示是否把我们的注解生成再java doc中、
@Documented
// Inherited 子类可以继承父类的注解
@Inherited
@interface MyAnnotation {
    
}
```

## Test03 自定义注解

```java
// 自定义注解
public class Test03 {

    // 注解可以显示赋值，如果没有默认值，可以必须给注解赋值
    @MyAnnotation2(name = "ahhTouPro", schools = {"南阳师范学院"})
    public void test() {
    }

    @MyAnnotation3("ahhTou")
    public void test2() {
    }


}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2 {

    // 注解的参数: 参数类型 + 参数名();
    String name() default "ahhTou";

    int age() default 0;

    int id() default -1; // 如果默认值为-1，代表不存在

    String[] schools();

}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3 {
    // 只有value可以省略
    String value();
}
```

# 反射

Reflection (反射)是Java被视为动态语言的关键，反射机制允许程序在执行期借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。

## Test01-02 得到反射

```java
        Person person = new Student();
        System.out.println("这个人是：" + person.name);

        // 方式1 通过对象获得
        Class<?> c1 = person.getClass();

        // 方式2 通过forName
        Class<?> c2 = Class.forName("com.ahhTou.reflection.Student");

        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());

        // 方式3 通过类名.class 获得
        Class<?> c3 = Student.class;

        // 方式4 通过类名的.class获得
        Class<Integer> c4 = Integer.TYPE;
        System.out.println(c4);

        // 获得父类的类型
        Class<?> superclass = c1.getSuperclass();
        System.out.println(superclass);
```

## Test03 那些对象可以Class对象

**class**:外部类，成员(成员内部类，静态内部类)，局部内部类，匿名内部类。
interface:接口
[]: 数组
enum:枚举
annotation:注解@interface
primitive type:基本数据类型
void

```java
Class<Object> c1 = Object.class; // 类
Class<?> c2 = Comparable.class; // 接口
Class<String[]> c3 = String[].class; // 一维数组
Class<int[][]> c4 = int[][].class; // 二维数组
Class<Override> c5 = Override.class; // 注解
Class<ElementType> c6 = ElementType.class; // 基本数据类型
Class<Void> c7 = void.class; // void
Class<?> c8 = Class.class; // Class

// 只要元素类型与维度一样，就是同一个class
int[] a = new int[10];
int[] b = new int[100];
System.out.println(a.getClass().hashCode());
System.out.println(b.getClass().hashCode()); // 相等
```

## Test04 类加载内存分析

```java
@SuppressWarnings("all")
public class Test04 {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
        /**
         * 1.加载刀内存，会产生一个类对应的Class对象
         * 2.链接，链接结束后 m= 0;
         * 3.初始化
         * <clinit>(){
         *              System.out.println("A类的无参构造初始化");
         *              m = 300;
         *              m = 100;
         * }
         *
         *
         */
    }
}

class A {

    // 初始化时 class的静态代码块会合并
    static {
        System.out.println("A类静态代牧初始化");
        m = 300;
    }

    static int m = 100;


    public A() {
        System.out.println("A类的无参构造初始化");
    }
}
```

##  Test05 分析类的初始化

### 类的主动引用

```java
class Father {

    static int b = 2;

    static {
        System.out.println("父类被加载");
    }
}

class Son extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }

    static int m = 100;
    static final int M = 1;
}
```

一定会发生类的初始化

```java
// 1. 主动引用
Son son = new Son(); 
// 反射也会产生主动引用
Class.forName("com.ahhTou.reflection.Son"); 

// 两者父类和子类都会被调用静态方法
```

### 类的被动引用

不会发生类的初始化化

```java
System.out.println(Son.b); // 子类没有被加载
Son[] array = new Son[5]; // 只是开辟了一个空间，并没有被加载
System.out.println(Son.M); // M被放到常量池里，并没有被加载
```

## Test06 类加载器

```java
    ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
    System.out.println(systemClassLoader);

    // 获取系统类加载器的父加载器 -> 扩展类加载器
    ClassLoader parent = systemClassLoader.getParent();
    System.out.println(parent);

    // 获取扩展类加载器的父类加载器 -> 根加载器（c/c++）
    ClassLoader parent1 = parent.getParent();
    System.out.println(parent1);

    // 测试当前类是哪个加载器加载的
    ClassLoader classLoader = Class.forName("com.ahhTou.reflection.Test07").getClassLoader();
    System.out.println(classLoader); // 系统类加载器

    // 测试Jkd内置的列是谁加载的
    classLoader = Class.forName("java.lang.Object").getClassLoader();
    System.out.println(classLoader); // 根加载器

    // 如何获得系统类加载器可以加载的的路径
    System.out.println(System.getProperty("java.class.path"));

    // 双亲委派机制
        // 保证安全性，加入上层有相同的类，类加载器会加载上层的类，而不会加载自己写的
}
```

## Test07 获取类的运行时结构

```java

        Class<?> c1 = Class.forName("com.ahhTou.reflection.User");

        // 得到类的名字
        System.out.println(c1.getName()); // 获得包名 + 类名
        System.out.println(c1.getSimpleName()); // 得到类名

        // 获得类的属性
        System.out.println("=============");
        Field[] field = c1.getFields(); // 只能找到public属性

        field = c1.getDeclaredFields(); // 能得到全部属性
        for (Field field1 : field) {
            System.out.println(field1);
        }

        // 获得指定属性的值
        Field name = null;
        // name = c1.getField("name"); // 报错
        name = c1.getDeclaredField("name");
        System.out.println(name);

        System.out.println("======");

        // 活得类的方法
        Method[] methods = c1.getMethods(); // 获得本类及其父类全部的public方法
        for (Method method : methods) {
            System.out.println("正常的 :" + method);
        }

        methods = c1.getDeclaredMethods(); // 获取本类的所有方法
        for (Method method : methods) {
            System.out.println("getDeclaredMethods :" + method);
        }

        // 获取指定的方法
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        System.out.println("获得指定的构造器");
        System.out.println("===============");
        Constructor[] constructors = c1.getConstructors(); // public
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        constructors = c1.getDeclaredConstructors();    // 本类的
        for (Constructor constructor : constructors) {
            System.out.println("    " + constructor);
        }

        // 获得指定的构造类
        Constructor<?> constructor = c1.getConstructor(String.class, int.class, int.class);
        Constructor<?> declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println("指定：" + constructor);
        System.out.println("指定declared：" + declaredConstructor);


```

## Test08 动态创建对象的执行方法

### 创建对象

```java
// 获得Class对象
Class<?> c1 = Class.forName("com.ahhTou.reflection.User");
// 构造一个对象
// 1. 类必须有一个无参构造器
// 2. 类的构造器访问权限必须足够
User user = (User) c1.newInstance(); // 本质调用了无参构造器

// 通过构造器创建对象
Constructor<?> constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
User user2 = (User) constructor.newInstance("ahhTou", 1, 1);
```

### 执行方法

```java
User user3 = (User) c1.newInstance();
Method setName = c1.getDeclaredMethod("setName", String.class);
setName.invoke(user3, "ahhTou");
```

### 修改属性

```java
System.out.println("===========");
User user4 = (User) c1.newInstance();
Field name = c1.getDeclaredField("name");
// name.set(user4, "ahhTouPro"); private不能直接操作

name.setAccessible(true); //取消安全属性
name.set(user4, "ahhTouPro");
System.out.println(user4);
```

## Test09 性能对比

Java代码

```java
// 普通方式调用
public static void test01() {
    User user = new User();
    // 反射方法调用
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < 1000000000; i++) {
        user.getName();
    }
    long endTime = System.currentTimeMillis();
    System.out.println("普通方式执行10亿次" + (endTime - startTime) + "ms");


}

// 反射方法调用，开启检测
public static void test02() throws Exception {
    User user = new User();
    Class c1 = user.getClass();
    Method getName = c1.getDeclaredMethod("getName", null);
    // 反射方法调用
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < 1000000000; i++) {
        getName.invoke(user, null);
    }
    long endTime = System.currentTimeMillis();
    System.out.println("开启检测 反射方式执行10亿次" + (endTime - startTime) + "ms");

}

// 反射方式调用，关闭检测
public static void test03() throws Exception {
    User user = new User();
    Class c1 = user.getClass();
    Method getName = c1.getDeclaredMethod("getName", null);
    getName.setAccessible(true);
    // 反射方法调用
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < 1000000000; i++) {
        getName.invoke(user, null);
    }
    long endTime = System.currentTimeMillis();
    System.out.println("关闭检测 反射方式执行10亿次" + (endTime - startTime) + "ms");

}

public static void main(String[] args) throws Exception {
    test01();
    test02();
    test03();
}
```

结果

```bash
普通方式执行10亿次2ms
开启检测 反射方式执行10亿次1682ms
关闭检测 反射方式执行10亿次1167ms
```



## Test10 获取泛型信息

参考java代码

## Test11 获取注解信息

```java
package com.ahhTou.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

// 练习反射操作注解
public class Test11 {

    public static void main(String[] args) throws Exception {
        Class<?> c1 = Class.forName("com.ahhTou.reflection.Student2");

        // 通过反射获取注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        // 获取注解的value的值
        TableAhhTou tableAhhTou = c1.getAnnotation(TableAhhTou.class);
        String value = tableAhhTou.value();
        System.out.println(value);

        // 获得类指定的注解
        Field f = c1.getDeclaredField("name");
        FieldAhhTou annotation = f.getAnnotation(FieldAhhTou.class);
        System.out.println(annotation.columnName());
        System.out.println(annotation.type());
        System.out.println(annotation.length());
    }

}

@TableAhhTou("db_student")
class Student2 {
    @FieldAhhTou(columnName = "db_id", type = "int", length = 10)
    private int id;
    @FieldAhhTou(columnName = "db_age", type = "int", length = 10)
    private int age;
    @FieldAhhTou(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student2() {
    }

    @Override
    public String toString() {
        return "Test11{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


// 类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableAhhTou {
    String value();
}

// 属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldAhhTou {
    String columnName();

    String type();

    int length();
}
```

