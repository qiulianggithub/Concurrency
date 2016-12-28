package com.atumu.concurrency.art;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore(信号量)是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源；
 * User: zhanglin
 * Date: 2016/3/24
 * Time: 10:31
 */
public class SemaphoreTest {

    private static final int Thread_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(Thread_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < Thread_COUNT; i++) {
            final int t = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        Thread.sleep(20);
                        System.out.println(t + " Save Data, queueLength " + s.getQueueLength());
                        s.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPool.shutdown();
    }
}
