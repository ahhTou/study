## 继承Thread类

继承并重写run方法

```java
public class TestThread1 extends Thread {

    // 重写run方法，作为程序主体
    @Override
    public void run() {

        for (int i = 0; i < 20; i++) {

            System.out.println("我在看代码---" + i);

        }
    }
}
```

使用

```java
testThread1.start(); // 开辟一个新的进程

testThread1.run(); // 普通方法调用
```

```
创建线程方式： 继承Thread类，重写run()方法，调用start()

总结： 注意，进程开启不一定立即执行，由cpu调度执行
```

### 小demo实现下载图片

查考代码 ===> TestThread2.java

## 继承Runnable类

TestThread3

```java
package com.ahhTou.demo01;

// 创建线程方式2 实现runnable接口  执行线程需要丢入runnable接口实现类
public class TestThread3 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码---" + i);

        }
    }

    public static void main(String[] args) {
        // 创建一个线程对象
        TestThread3 testThread3 = new TestThread3();
        // 创建线程对象，通过线程对象来开启我们的线程，代理
        new Thread(testThread3).start();

        // 调用start()方法开启线程
        for (int i = 0; i < 20; i++) {

            System.out.println("我在学习多线程--" + i);
        }
    }
}
```

### 龟兔赛跑

com/ahhTou/demo01/TestThread5Race.java:12

## 实现Callable接口

```java
public class TestCallable implements Callable<?> {
    @Override
    public <?> call() {
        return ?;
    }
}
```

```java
ExecutorService ser = Executors.newFixedThreadPool(3);

// 提交执行
Future<Boolean> r1 = ser.submit(t1);
Future<Boolean> r2 = ser.submit(t2);
Future<Boolean> r3 = ser.submit(t3);

// 获取结果
boolean res1 = r1.get();
boolean res2 = r2.get();
boolean res3 = r3.get();

// 关闭服务
ser.shutdown();
```

## 静态代理

```java
package com.ahhTou.proxy_static;

//萨态代理模式总结:
//真实对象和代理对象都要实现同-一个按口
public class StaticProxy {
    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

// 真实角色 你去结婚
class You implements Marry {
    @Override
    public void HappyMarry() {
        System.out.println("秦老师要结婚了，超开心");
    }
}

// 代理角色，帮助你结婚
class WeddingCompany implements Marry {

    // 代理谁 -> 真实目标角色
    private final Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void before() {
        System.out.println("结婚之前：布置现场");
    }

    private void after() {
        System.out.println("结婚之后：收尾款");
    }
}
```

### 对比Thread接口

```
new Thread(() ->
        System.out.println("我杀了你") // 代理对象
).start();


new WeddingCompany(
        new You() // 代理对象
).HappyMarry();
```

## Lambda

- 为什么要使用lambda表达式
    - 避免匿名内部类定义过多
    - 可以让你的代码看起来很简洁
    - 去掉了一堆没有意义的代码，只留下核心的逻辑。

```java
package com.ahhTou.Lamda;

public class TestLambda2 {


    public static void main(String[] args) {
        
        ILove love = new ILove() {
            @Override
            public void love(int a) {
                System.out.println("I love you -->" + a);
            }
        };
        
        ILove love = (int a) -> {
            System.out.println("I love you -->" + a);
        };

        love = a -> {
            System.out.println("I love you -->" + a);
        };

        love = a -> System.out.println("I love you -->" + a);

        love.love(2);
        /*
        总结：
            lambda表达式只能有一行代码的情况 下才能简化成为-行，如果有多行，那么就用代码块包裹。
            前提是接口为函数式接口

         */


    }
}
interface ILove {
    void love(int a);
}
```

## 线程的五大状态

### 停止

```java
// 测试stop
// 1. 建议线程正常停止 --> 不建议死循环
// 2. 建议使用标志位 --> 设置一个标注位
// 3. 不要使用stop和destroy等过时或者jdk比建议使用的方法
public class TestStop implements Runnable {

    // 设置一个标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run...Thread" + i++);
        }
    }

    // 自己调用停止方法
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {

        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        
        testStop.stop(); // 手动调用自己写的停止方法
    }
}
```

### 休眠

> Sleep不会释放锁

```java
package com.ahhTou.state;

import java.text.SimpleDateFormat;
import java.util.Date;

// 模拟倒计时
public class TestSleep {
    public static void main(String[] args) {

        // 打印系统当前时间
        Date statTime = new Date(System.currentTimeMillis());
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(statTime));
                statTime = new Date(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void tenDown() throws InterruptedException {
        int num = 10;
        do {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "-->" + num--);
        } while (num > 0);
    }

}
```

### 礼让

```java
package com.ahhTou.state;

// 测试礼让线程
// 礼让不一定成功，看cpu心情
public class TestYield {

    public static void main(String[] args) {
        MyYield myYield = new MyYield();

        new Thread(myYield,"A").start();
        new Thread(myYield,"B").start();
    }

}

class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程停止执行");
    }
}
```

礼让成功：

```bash
A线程开始执行
B线程开始执行
A线程停止执行
B线程停止执行
```

### 强制执行

```java
package com.ahhTou.state;

// 测试Join方法 // 想象为插队
public class TestJoin implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程Vip来了" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 启动我们的线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        // 主线程
        for (int i = 0; i < 1000; i++) {

            if (i == 200) {
                thread.join(); //插队
            }
            System.out.println("main" + i);
        }

    }
}
```

### 如何观测

```java
// 观察测试线程状态
public class TestState {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("////////////");
        });
        Thread.State state = thread.getState();
        System.out.println(state); // new

        // 观察启动后
        thread.start(); // 启动线程
        state = thread.getState();
        System.out.println(state); // Run

        while (state != Thread.State.TERMINATED) {
            Thread.sleep(100);
            state = thread.getState();
            System.out.println(state);
        }

    }

}
```

### 设置优先级

先设置优先级，再启动

优先级低只是意味着获得调度的概率低.并不是优先级低就不会被调用了.这都是看CPU的调度

```java
package com.ahhTou.state;

public class TestPriority {

    // 主线程默认为 5
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " --> " + Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();
        Thread t1 = new Thread(myPriority);
        Thread t2 = new Thread(myPriority);
        Thread t3 = new Thread(myPriority);
        Thread t4 = new Thread(myPriority);
        Thread t5 = new Thread(myPriority);
        Thread t6 = new Thread(myPriority);

        // 先设置优先级，再启动
        t1.start();

        t2.setPriority(1);
        t2.start();

        t3.setPriority(4);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();

        t5.setPriority(-1);
        t5.start();

        t6.setPriority(11);
        t6.start();
    }

}

class MyPriority implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " --> " + Thread.currentThread().getPriority());
    }
}
```

### 守护线程

- 线程分为用户线程和守护线程
- 虚拟机必须确保用户线程执行完毕
- **虚拟机不用等待守护线程执行完毕**
- 如，后台记录操作日志，监控内存，垃圾回收等待..

```java
package com.ahhTou.state;

// 测试守护线程
// 上帝守护你
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true); // 默认是false表示是用户线程，正常线程都说守护线程

        thread.start(); // 上帝守护着你

        new Thread(you).start(); // 你 用户线程停止
    }

}

// 上帝
class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑着你");
        }
    }
}

// 你
class You implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都开心的或者");
        }
        System.out.println("=== good bye ! world ");
    }
}
```

## 线程同步

### 不安全案例

- 买票
- 取钱
- 不安全的集合

### 锁

- 锁对象（操作的是对象中的方法）

```java
private synchronized void buy() {
    // 时候有票
    if (ticketNums <= 0) {
        flag = false;
        return;
    }
    // 买票
    System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
}
```

- 锁代码块

```java
synchronized (account) {
    // 判断有没有钱
    if (account.money - drawingMoney < 0) {
        System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
        return;
    }

    try {
        Thread.sleep(1000);
    } catch (Exception e) {
        e.printStackTrace();
    }

    account.money = account.money - drawingMoney; // 卡里的余额

    nowMoney = nowMoney + drawingMoney; // 你手里的钱

    System.out.println(account.name + " 的余额为 ：" + account.money);

    System.out.println(this.getName() + "手里的钱：" + nowMoney);
}
```

```java
synchronized (list) {
    list.add(Thread.currentThread().getName());
}
```

### CopyOnWriteArrayList

线程安全的ArrayList

```java
public class TestJUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
    }
}
```

## Lock锁

```JAVA
private final ReentrantLock lock = new ReentrantLock();

@SuppressWarnings("all")
@Override
public void run() {
    while (true) {
        try {
            lock.lock();
            if (ticketNums > 0) {
                Thread.sleep(1000);
                System.out.println(ticketNums--);
            } else {
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```

## 线程池

```java
package com.ahhTou.high_Level;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 测试线程池
public class TestPool {

    public static void main(String[] args) {
        // 1. 创建服务，创建线程词
        // new FixedThreadPool 参数为： 线程池大小
        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        // 2. 关闭链接
        service.shutdown();
    }

}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
```

## 总结

```java
package com.ahhTou.high_Level;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadNew {
    public static void main(String[] args) {
        new MyThread1().start();

        new Thread(new MyThread2()).start();

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread3());
        new Thread(futureTask).start();

        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

// 1. 继承 Thread 类
class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread1");
    }
}

// 2. 实现 Runnable 接口
class MyThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("MyThread2");
    }
}

// 3. 实现 Callable 接口
class MyThread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("MyThread3");
        return 100;
    }
}
```

