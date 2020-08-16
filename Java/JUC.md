# 1、什么是JUC

（面试高频问）

java.util 工具包

**业务: 普通的线程代码Thread**

*Runnable** 没有返回值、效率相比入Callable 相对较低!

# 2、线程和进程

> 线程、进程

**进程**：一个程序，qq.exe, 程序的集合;

一个进程，往往可以包含多个进程，至少包含一个

Java默认有几个线程？2个 一个main线程，GC线程（垃圾回收等）

**线程**：开了一个进程Typora，写字，自动保存(线程负责的)

对于Java而言 Thread、Runnable、Callable

**Java真的开启线程吗？**开不了

本地方法，底层的C++，Java 无法直接操作硬件
`private native void start0();`



> 并发，并行

**并发编程： 并发、并行的区别**

并发（多线程操作同一个资源）、

- Cpu 一核，模拟出多条线程，快速交替，产生多线程

并行（多个人一起行走）

- Cpu多核，多个线程可以同时执行；线程池



并发编程本质：**充分利用cpu的资源**

所有的公司都很看重！



> 线程有几个状态

新建 New、

运行 Runnable、

阻塞 Bolcked、

等待 Waiting（超时等待）、

超时等待 timed_waiting、

死亡  Terminated



> wait/ sleep

**1、来自不同的类**

wait => Object

sleep => Thread（**一般不用**）



**2、锁的释放**

wait会释放锁，sleep抱着锁睡觉，不会释放



**3、使用的范围不一样**

wait 这能在同步代码块中使用

sleep可以在任何地方睡



**4、是否需要捕获异常**

wait 不需要捕获异常

sleep 必须要捕获异常



# 3、Lock锁

## 传统的 Synchronized

公平锁：十分公平，可以先来后到

**非公平锁：十分不公平 可以插队（默认）**

synchronized

```java
class Ticket {
    private int number = 50;
    // synchronized 本质 队列 和 锁
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "第几张票");
        }
    }
}
```

lock

```java
// lock 三部曲
// 1. new ReentrantLock
// 2. lock.lock() 加锁
// finally ==> lock.unlock();
class Ticket2 {
    private int number = 50;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            //业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "第几张票");
            }
        } finally {
            lock.unlock();
        }
    }

}
```

## Synchronized 和 lock的区别

- Synchronized 内置的java关键字，lock 是一个java类

- Synchronized 无法判断获取锁的状态，lock 可以判断是否获取的锁
- Synchronized 会自动释放锁，lock 必须手动解锁，如果不释放锁，**死锁**
- Synchroniezd 线程1（获取锁、阻塞）、线程2（一直等待）；lock锁不会一直等待lock.tryLock()
- Synchronized 可重入锁，不可以中断，非公平；lock，可重入锁，可以判断锁，非公平（可以更改）
- Synchronized 适合少量的代码问题，lock锁 适合锁代码的同步代码



## 锁是什么。如何判断锁的是谁!



# 4、生产者和消费者

## Synchronized

```java
public class A {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

// 判断等待，业务，通知
class Data { // 数字 资源类

    private int number = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        if (number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " => " + number);
        // 通知其他线程，我+1 完毕了
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        if (number == 0) {
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " => " + number);
        // 通知其他线程，我-1 完毕了
        this.notifyAll();
    }

}
```

### 防止虚假唤醒

```java
package com.ahhTou.pc;

import java.util.Date;

/**
 * 线程之间的通信问题 -> 生产者和消费者的问题
 * 线程交替执行 A B操作同一个变量 num = 0
 * A num + 1
 * B num - 1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}

// 判断等待，业务，通知
class Data { // 数字 资源类

    private int number = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " => " + number);
        // 通知其他线程，我+1 完毕了
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " => " + number);
        // 通知其他线程，我-1 完毕了
        this.notifyAll();
    }

}
```

## JUC

```java
package com.ahhTou.pc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间的通信问题 -> 生产者和消费者的问题
 * 线程交替执行 A B操作同一个变量 num = 0
 * A num + 1
 * B num - 1
 */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "A2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "B2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "C2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "D2").start();
    }

}

// condition.await(); // 等待
// condition.signalAll(); // 唤醒全部
class Data2 { // 数字 资源类

    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public  void increment() {
        lock.lock();
        try {
            while (number != 0) {
                // 等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " => " + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void decrement() {
        lock.lock();
        try {

            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " => " + number);
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
```

### condition的优势

- 精准的通知和唤醒进程

```java
package com.ahhTou.pc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A 执行完调用B B执行完调用C C执行完A
 */
public class C {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();
    }
}

class Data3 { // 资源类
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1; // 1a 2b 3c

    public void printA() {
        lock.lock();
        try {
            // 业务代码， 判断 -> 执行 -> 通知
            while (number != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>A");
            // 唤醒指定的人B

            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB() {
        lock.lock();
        try {
            // 业务代码， 判断 -> 执行 -> 通知
            while (number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>B");
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printC() {
        lock.lock();
        try {
            // 业务代码， 判断 -> 执行 -> 通知
            while (number != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>C");
            number = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
```

# 5、8锁现象

- synchronized 普通方法 锁 实例
- synchronized 静态方法 锁 Class

# 6、集合类不安全

```java
List<String> list = new ArrayList<>(); // 不安全
List<String> list = new Vector<>();
List<String> list = Collections.synchronizedList(new ArrayList<>());
List<String> list = new CopyOnWriteArrayList<>();
```

## CopyOnWriteArrayList

```java
List<String> list = new ArrayList<>();
List<String> list = new Vector<>();
List<String> list = Collections.synchronizedList(new ArrayList<>());
List<String> list = new CopyOnWriteArrayList<>();
```

- CopyOnWrite 写入时复制 cow 计算机设计领域的一种优化策略
- 多个线程调用的时候，list, 读取的时候，固定的，写入(覆盖)
- 再写入到时候避免覆盖, 造成数据问题
- 读写分离
- 使用lock锁

优势（对比Vector）

- 没有使用synchronized锁，使用lock并在添加时先复制一份

## CopyOnWriteArraySet

```java
Set<String> set = new HashSet<>();
Set<String> set = Collections.synchronizedSet(new HashSet<>());
Set<String> set = new CopyOnWriteArraySet<>();
```

- HashSet底层是HashMap

```java
public HashSet() {
    map = new HashMap<>();
}
// add set 本质就是map ，key是无法重复的
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}
```

## ConcurrentHashMap

```java
Map<String, String> map = new HashMap<>();
Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

Map<String,String> map = new ConcurrentHashMap<>();
```

# 7、Callable

- 有返回值

- 可以抛出异常

- 方法不同 run()/ call()

- 细节

    - get方法会产生阻塞

    - 结果会被缓存，效率高，只会执行一次

        ```java
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "A").start(); // 结果会被缓存，效率高
        ```

```java
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start(); // Runnable实现类
        // new Thread(new FutureTask<V>( Callable )).start(); // Runnable实现类

        MyThread thread = new MyThread();

        FutureTask<String> futureTask = new FutureTask<>(thread);

        new Thread(futureTask, "A").start();

        String s = futureTask.get(); // 可能会产生阻塞

        System.out.println(s);

    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("小夫，我进来了");
        return "ahhTou";
    }
}
```

# 8、常用辅助类

## CountDownLatch

- 允许一-个或多个线程等待直到在其他线程中执行的一组操作完成的同步辅助。

    ```java
    package com.ahhTou.add;
    
    
    import java.util.concurrent.CountDownLatch;
    
    // 计数器
    public class CountDownLatchDemo {
        public static void main(String[] args) throws InterruptedException {
            // 总数是6
            CountDownLatch countDownLatch = new CountDownLatch(6);
    
            for (int i = 0; i < 6; i++) {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "Go out");
                    countDownLatch.countDown();
    
                }, String.valueOf(i)).start();
            }
            countDownLatch.await(); // 等待计数器归零再向下执行
    
    
            System.out.println("close Door");
    
        }
    }
    ```


## Semaphore

```java
    CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
        System.out.println("召唤神龙成功");
    });
    for (int i = 0; i < 8; i++) {
        final int temp = i; //1.8不用写
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "收集到了龙珠" + temp);

            try {
                cyclicBarrier.await(); // 当计数为7时将被唤醒
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
```

## CyclicBarrier

```java
// 线程数量 ： 停车位! 限流!
Semaphore semaphore = new Semaphore(3);

for (int i = 1; i <= 6; i++) {
    // acquire() 得到
    // release() 释放
    new Thread(() -> {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + "抢到车位");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + "离开车位");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }).start();
}
```

`semaphore.acquire()`获得,假设如果已经满了，等待 ,等待被释放为止!

`semaphore.release()`释放,会将当前的信号量释放+ 1 ,然后唤醒等待的线程!

# 9、读写锁

- 独占锁 ( 写锁 ) 一次只能被一个线程占用
- 共享锁 ( 读锁 ) 多个线程可以同时咱有
- 读 - 读 可以共存!
- 读 - 写 不能共存!
- 写 - 写 不能共存!

```java
// 读写锁
private final ReadWriteLock lock = new ReentrantReadWriteLock();

// 存，写
public void put(String key, Object value) {
    lock.writeLock().lock();
    try {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        lock.writeLock().unlock();

    }

}

// 取，读
public void get(String key) {
    try {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取Ok");

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        lock.readLock().unlock();
    }


}
```

# 10、阻塞队列

## BlockingQueue

- Queue
    - **BlockingQueue** 阻塞队列不是新的东西
        - LinkedBlockingQueue
        - ArrayBlockingQueue
    - Deque 双端队列
    - AbstractQueue 非阻塞队列

什么情况下我们会使用阻塞队列：对线程并发处理，线程池

**Api**

| 方式         | 抛出异常 | 有返回值 | 阻塞 | 超时等待  |
| ------------ | -------- | -------- | ---- | --------- |
| 添加         | add      | offer    | put  | offer(,,) |
| 移除         | remove   | poll     | take | poll(,)   |
| 返回队首元素 | element  | peek     |      |           |

```java
ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

// 抛出异常
blockingQueue.add("a");
blockingQueue.remove();
blockingQueue.element();

// 仅返回值
blockingQueue.offer("a");
blockingQueue.poll();
blockingQueue.peek();


// 阻塞
blockingQueue.put("a");
blockingQueue.take();
blockingQueue.offer("d", 2, TimeUnit.SECONDS); // 等待2秒停止退出
blockingQueue.poll(2, TimeUnit.SECONDS);
```

## SynchronousQueue

没用容量

进去一个元素，必须等待取出来，才能在网里面放一个元素

put take

- 同步队列
- 和其他BlockingQueue 不一样，SynchronousQueue不储存元素
- put了一个元素，必须先从里面take出来，否则不能put进去值
- 该demo会出现每隔一秒才能put（下一条put收到阻塞）

```java
SynchronousQueue<String> blockingQueue = new SynchronousQueue<>();

new Thread(() -> {
    try {
        System.out.println(Thread.currentThread().getName() + " put1");
        blockingQueue.put("1");
        System.out.println(Thread.currentThread().getName() + " put2");
        blockingQueue.put("1");
        System.out.println(Thread.currentThread().getName() + " put3");
        blockingQueue.put("1");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}, "T1").start();
new Thread(() -> {
    try {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + " " +blockingQueue.take());

        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + " " +blockingQueue.take());

        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + " " +blockingQueue.take());
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

}, "T2").start();
```

# 11、线程池

线程池：三大方法、七大参数、四种拒绝策略

> 池化技术

程序的运行, 本质 : 占用系统的资源 ! 优化资源的使用! =>池化技术

线程池、连接池、内存池、对象池

池化技术 : **事先准备好一些资源， 有人要用,就来我这里拿，用完之后还给我。**

- 默认大小
- 最大大小



**线程池的好处**

- 降低资源的消耗（**创建、销毁。十分浪费资源**）
- 提高相应的速度
- 方便管理
- **线程复用，可以控制最大并发数，管理线程**

## 三大方法

```java
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单个线程

//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// 创建一个固定的线程池的大小

        ExecutorService threadPool = Executors.newCachedThreadPool();// 可伸缩的，遇强则强，遇弱则弱

        for (int i = 0; i < 100; i++) {
            // 使用了线程池之后，使用线程池来创建线程
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " Ok");
            });
        }

        // 线程池用完，程序结束，关闭线程池
        threadPool.shutdown();
```

## 七大参数

- 源码分析

```java
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}

public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}

public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
```

- 本质调用 ThreadPoolExecutor

    ```java
    public ThreadPoolExecutor(int corePoolSize, // 核心线程池大小
                              int maximumPoolSize, // 最大核心线程池大小
                              long keepAliveTime, // 超时了没有人调用就会释放
                              TimeUnit unit,	// 超时单位
                              BlockingQueue<Runnable> workQueue, // 阻塞队列
                              ThreadFactory threadFactory, // 线程工厂，一般不用动
                              RejectedExecutionHandler handler // 拒绝策略) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
    ```

> 手动创建线程池

```java
ThreadPoolExecutor threadPool1 = new ThreadPoolExecutor(
        2,
        5,
        3,
        TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(3), // 银行候客厅
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.AbortPolicy() // 银行满了，还有人进来，不处理抛出异常
);
```

- 最大承载 ： 队列 + max值

- 拒绝策略

    - ```java
        new ThreadPoolExecutor.AbortPolicy(); // 银行满了，还有人进来，不处理抛出异常
        new ThreadPoolExecutor.CallerRunsPolicy(); // 回到原来的线程执行
        new ThreadPoolExecutor.DiscardPolicy(); // 队列满了，丢到任务，不会抛出异常
        new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试和最早的任务竞争
        ```

- 最大线程到底该如何定义

    - 1、CPU 密集型，几核，就是几，可以保持CPu的效率最高!

        ```
        System.out.println(Runtime.getRuntime().availableProcessors());
        final int MAX_CORE = Runtime.getRuntime().availableProcessors();
        
        ThreadPoolExecutor threadPool1 = new ThreadPoolExecutor(
                2,
                MAX_CORE,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), // 银行候客厅
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试和最早的任务竞争
        );
        ```

    - 2、IO密集型  判断你的程序中十分耗IO的线程，一般设置成两倍

        - 程序，15个大型任务，io十分占资源

# 12、四大函数式接口

> 函数式接口:只有一个方法的接口

- 超级多FunctionalInterface
- 简化编程模型，在新版本的框架底层大量应用!

## Function函数式

- 函数型接口

- ```java
    Function<String, String> function = new Function<String, String>() {
        @Override
        public String apply(String str) {
            return str;
        }
    };
    
    Function function1 = (str) -> str;
    
    System.out.println(function.apply("啦啦啦"));
    System.out.println(function1.apply("呜呜呜"));
    ```

## Predicate断定型

```java
Predicate<String> fun1 = new Predicate<String>() {
    @Override
    public boolean test(String s) {
        return s.isEmpty();
    }
};

Predicate<String> fun2 = String::isEmpty;

System.out.println(fun1.test("fun1"));
System.out.println(fun2.test("fun1"));
```

## Consumer消费型

- 只有输入没有输出

```java
Consumer<String> consumer = new Consumer<String>() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
};

Consumer<String> consumer1 = System.out::println;


consumer.accept("ahhTou");
consumer.accept("ahhTouPro");
```

## Supplier供给型

- 只有返回值，没有参数

```java
Supplier<Integer> supplier = new Supplier<Integer>() {
    @Override
    public Integer get() {
        return 1024;
    }
};


Supplier<Integer> supplier1 = () -> 999;

System.out.println(supplier.get());
System.out.println(supplier1.get());
```

# 13、Stream流式计算

> 什么是流式计算

类

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private int id;
    private String name;
    private int age;
}
```

题目

- 要求:一 分钟内完成此题，只能用一行代码实现!
- 现在有5个用户!筛选:
    - ID必须是偶数
    - 年龄必须大于23岁
    - 用户名转为大写字母
    - 用户名字母倒着排序
    - 只输出一个用户!

```java
User u1 = new User(1, "a", 21);
User u2 = new User(2, "b", 22);
User u3 = new User(3, "c", 23);
User u4 = new User(4, "d", 24);
User u5 = new User(6, "e", 25);

List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

// 计算交给Stream流
// Lambda表达式、链式编程、函数式接口、Stream流式计算
list.stream()
        .filter(user -> user.getId() % 2 == 0)
        .filter(user -> user.getAge() > 23)
        .map((user -> user.getName().toUpperCase()))
        .sorted(String::compareTo)
        .limit(1)
        .forEach(System.out::println);
```

# 14、ForkJoin

ForkJoin 在JDK1.7 ，并行执行任务!提高效率。大数据量!

大数据：Map Reduce( 把大任务拆成小任务 )

> 特点

工作窃取

> 操作

- ForkJoinPool 通过它来执行
- 计算任务 ForkJoinTask\<V>
    - RecuresiveActive 递归事件，没有返回值
    - RecursiveTask 递归任务，有返回值

> 方法类

```java
package com.ahhTou.forkjoin;



import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {
    private final long start;
    private final long end;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 临界值
        long temp = 10_0000L;
        if ((end - start) < temp) {
            System.out.println(Thread.currentThread().getName());
            long sum = 0L;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 分支合并计算
            System.out.println(Thread.currentThread().getName());
            long middle = (start + end) / 2; // 中间值

            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);

            task1.fork(); // 拆分任务，把任务压入线程队列
            task2.fork();
            return task1.join() + task2.join();
        }
    }


}
```

> 测试

```java
public static long endNum = 10_0000_0000_0L;

public static void main(String[] args) throws ExecutionException, InterruptedException {
    test1();
    test2();
    test3();
}

// 普通程序员
public static void test1() {
    long sum = 0L;
    long start = System.currentTimeMillis();
    for (long i = 0; i < endNum; i++) {
        sum += i;
    }
    long end = System.currentTimeMillis();
    System.out.println("sum=" + sum + " 时间：" + (end - start));
}

// 会使用ForkJoin
public static void test2() throws ExecutionException, InterruptedException {
    long start = System.currentTimeMillis();
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    ForkJoinTask<Long> task = new ForkJoinDemo(0L, endNum);
    ForkJoinTask<Long> submit = forkJoinPool.submit(task);// 提交任务

    long sum = submit.get();

    long end = System.currentTimeMillis();
    System.out.println("sum=" + sum + " 时间：" + (end - start));
}

// 普通程序员
public static void test3() {
    long start = System.currentTimeMillis();

    long sum = LongStream.rangeClosed(0L, endNum).parallel().reduce(0, Long::sum);

    long end = System.currentTimeMillis();
    System.out.println("sum=" + sum + " 时间：" + (end - start));
}
```

# 15、异步回调

```java
// 没有返回值的异步回调
CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
    try {
        TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
});

System.out.println("111");

completableFuture.get();

// 有返回结果的
CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
    System.out.println(Thread.currentThread().getName() + "supplyAsync=>Void");
    return 1024;
});

System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
    System.out.println("t => " + t);
    System.out.println("u => " + u);
}).exceptionally((e) -> {
    System.out.println(e.getMessage());
    return 233;
}).get());
```

# 16、JMM

> 请你谈谈你对Volatile的理解

Volatile是Java虚拟机提供**轻量级的同步机制**

- 保证可见性

- **不保证原子性**

- 禁止指令重排

> 什么是JMM

JVM： Java内存模型,不存在的东西,概念!约定!



**关于JMM的一些同步的约定:**

- 线程解锁前,必须把共享变量**立刻**刷回主存。
- 线程加锁前,必须读取主存中的最新值到工作内存中!

- 加锁和解锁是同一把锁



线程**工作内存**、**主内存**

# 17、Volatile

## 可见性

```java
// 不加 volatile 程序就会进行死循环
// 加 volatile 会保证 可见性
private static volatile int num = 0; //volatile关键字可以保证变量改变时被其他线程感知

public static void main(String[] args) throws InterruptedException {
    // main 线程

    new Thread(() -> { // 线程1 对主内存的变坏不知道
        while (num == 0) {

        }
    }).start();

    TimeUnit.SECONDS.sleep(1);

    num = 1; // 线程应当停止
    System.out.println(num);
}
```

## 不保证原子性

原子性：不可分割

线程A在执行任务的时候,不能被打扰的,也不能被分割。要那么同时成功，要么

```java
private volatile static int num = 0;

public static void add() {
    num++;
}

public static void main(String[] args) {

    for (int i = 0; i < 20; i++) {
        new Thread(() -> {

            for (int j = 0; j < 1000; j++) {
                add();
            }

        }).start();
    }

    while (Thread.activeCount() > 2) {
        Thread.yield();
    }

    System.out.println(Thread.currentThread().getName() + " " + num);
	// 结果依然无法达到20000

}
```

> 什么原因

++操作并非原子性操作

- 获取值
- +1
- 写回这个值

> 如何解决

使用原子类解决

```java
private static final AtomicInteger num = new AtomicInteger();

public static void add() {
    num.getAndIncrement();// 不是原子性操作
}
```

这些类的底层都直接和操作系统挂钩!在内存中修改值!



> 指令重排

什么是指令重排:**你写的程序.计算机并不是按照你写的那样去执行的。**

源代码 --> 编译器优化的重排 --> 指令并行也可能会重排 --> 内存系统也会重排 --> 执行

| 线程A | 线程B |
| ----- | ----- |
| x=a   | y=b   |
| b=1   | a=2   |

正常的结果: x=0; v=0 ;但是可能由于指令重排

| 线程A | 线程B |
| ----- | ----- |
| b=1   | a=2   |
| x=a   | y=b   |

指令重排导致的诡异结果: x=2;y= 1



**volatile可以避免指令重排**

内存屏障。CPU指令。作用：

- 保证特定的执行顺序
- 可以保证某些变量的内存可见性( 利用这些特性，就可以保持)



**Volatile是可以保持可见性。不能保证原子性,由于内存屏障,可以保证避免指令重排的现象产生!**

# 18、彻底玩转单例模式

## 饿汉式

消耗资源

```
// 饿汉式单例
public class Hungry {


    private Hungry() {

    }

    private final static Hungry HUNGRY = new Hungry();

    private static Hungry getInstance() {
        return HUNGRY;
    }

}
```

## 懒汉式

加锁保证单例，加Volatile保证指令重排，空指针异常

```java
// 懒汉式单例
public class LazyMan {

    private LazyMan() {
        System.out.println(Thread.currentThread().getName());
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
```

## 静态内部类

```java
private Holder() {

}

public static Holder getInstance() {
    return InnerClass.HOLDER;
}

public static class InnerClass {
    private static final Holder HOLDER = new Holder();
}
```

## 懒汉式出现的问题

反射破坏单例模式 反射不安全

```java
LazyMan instance = LazyMan.getInstance();
Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
declaredConstructor.setAccessible(true);
LazyMan lazyMan = declaredConstructor.newInstance();
System.out.println(instance);
System.out.println(lazyMan);
```

## 枚举

枚举类型的最终反编译有构造函数但是是私有的

反射又无法为枚举创建实例

缺点:

- 循环会耗时
- 一次在哪保证一个共享变量的原子性
- ABA问题

# 19、深入理解CAS

> 什么是CAS

unsafe类中直接操作内存

比较当前工作内存中的值和主内存中的值,如果这个值是期望的,那么则执行操作!如果不是就一直循环!

> CAS：ABA问题

狸猫换太子,正常执行

```java
System.out.println(atomicInteger.compareAndSet(2020, 2021));
System.out.println(atomicInteger.get());

System.out.println(atomicInteger.compareAndSet(2021, 2020));
System.out.println(atomicInteger.get());


System.out.println(atomicInteger.compareAndSet(2020, 6666));
System.out.println(atomicInteger.get());
```

# 20、原子操作

> 解决ABA问题，引入原子操作

**所有的相同类型的包装类对象之间值的比较，全部使用equals方法比较。**
说明:对于Integer var = ?在-128 至127之间的赋值，Integer 对象是在
IntegerCache . cache产生，会复用已有对象，这个区间内的Integer值可以直接使用==进行
判断，但是这个区间之外的所有数据,都会在堆上产生,并不会复用己有对象,这是一个大坑，
推荐使用equals方法进行判断。



```java
// CAS  compareAndSet 比较并交换
public static void main(String[] args) {

    // int Integer 如果泛型是一个包装类，注意对象引用问题
    AtomicStampedReference<Integer> atomicInteger
            = new AtomicStampedReference<>(1, 1);

    /*
     * ABA
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
```

# 21、各种锁的理解

## 1、公平锁、非公平锁

公平锁:非常公平。不能够插队,必须先来后到

非公平锁:非常不公平,可以插队( 默认都是非公平)

```java
// 默认 非公平锁
public ReentrantLock() {
    sync = new NonfairSync();
}

// 设置是否为公平锁
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```

## 2、可重入锁

可重入锁（递归锁），

```java
public class Demo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sms();
        }, "A").start();


        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + " -> sms");
        call(); // 这里也有锁
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " -> call");

    }
}
```

结果固定不变

```bash
A -> sms
A -> call
B -> sms
B -> call
```

## 自旋锁

spinlock

```java
public class SpinlockDemo {
    // int 0
    // Thread null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "==> myLock");

        // 自选锁
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }

    // 解锁
    public void unLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "==> myUnLock");
        atomicReference.compareAndSet(thread, null);
    }

    public void test(Boolean b) {
        while (b) {

        }
        System.out.println("dead");
    }

}

class TestDemo {
    public static void main(String[] args) throws InterruptedException {
        SpinlockDemo lock = new SpinlockDemo();


        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        }, "T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();

            lock.unLock();
        }, "T2").start();
    }
}
```

## 死锁

排查死锁

- 查看日志
- 查看堆栈信息



使用 jps -l 查看进程

使用 jstack [端口号] 使用堆栈信息

```bash
"T2":
  waiting to lock monitor 0x000000001c3d01f8 (object 0x000000076b1b4198, a java.lang.Object),
  which is held by "T1"
"T1":
  waiting to lock monitor 0x000000001c3cebf8 (object 0x000000076b1b41a8, a java.lang.Object),
  which is held by "T2"

Java stack information for the threads listed above:
===================================================
"T2":
        at com.ahhTou.lock.MyThread.run(DeadLockDemo.java:38)
        - waiting to lock <0x000000076b1b4198> (a java.lang.Object)
        - locked <0x000000076b1b41a8> (a java.lang.Object)
        at java.lang.Thread.run(Thread.java:745)
"T1":
        at com.ahhTou.lock.MyThread.run(DeadLockDemo.java:38)
        - waiting to lock <0x000000076b1b41a8> (a java.lang.Object)
        - locked <0x000000076b1b4198> (a java.lang.Object)
        at java.lang.Thread.run(Thread.java:745)

Found 1 deadlock.
```

