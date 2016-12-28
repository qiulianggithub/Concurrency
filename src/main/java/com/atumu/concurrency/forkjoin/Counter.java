package com.atumu.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 使用fork/join框架，求和
 * User: zhanglin
 * Date: 2016/3/24
 * Time: 9:39
 */
public class Counter extends RecursiveTask<Integer> {

    private static final long THRESHOLD = 1000;

    private long start;
    private long end;

    public Counter(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (start - end) <= THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long middle = (start + end) / 2;
            Counter left = new Counter(start, middle);
            Counter right = new Counter(middle + 1, middle);
            left.fork();
            right.fork();
            Integer leftRresult = left.join();
            Integer rightRresult = right.join();

            sum = leftRresult + rightRresult;
        }

        return sum;
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                int sum = 0;
                for (long i = 1; i <= Integer.MAX_VALUE; i++) {
                    sum += i;
                }
                System.out.println("1: " + sum);
                System.out.println("cost time " + (System.currentTimeMillis() - start) + "ms");
            }
        }).start();


        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        Counter counter = new Counter(1, Integer.MAX_VALUE);
        ForkJoinTask<Integer> result = pool.submit(counter);

        try {
            System.out.println("2: " + result.get());
            System.out.println("cost time " + (System.currentTimeMillis() - start) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


}
