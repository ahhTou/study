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
//            System.out.println(Thread.currentThread().getName());
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 分支合并计算
//            System.out.println(Thread.currentThread().getName());
            long middle = (start + end) / 2; // 中间值

            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);

            task1.fork(); // 拆分任务，把任务压入线程队列
            task2.fork();
            return task1.join() + task2.join();
        }
    }


}
