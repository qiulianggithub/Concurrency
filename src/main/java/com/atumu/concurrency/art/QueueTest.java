package com.atumu.concurrency.art;

import java.util.concurrent.*;

/**
 * 队列相关测试
 * <p/>
 * 1.
 * Queue:单向队列，FIFO;
 * Deque:双向队列，对Queue接口的增强；
 * 2.
 * CountDownLatch 计数锁
 * User: zhanglin
 * Date: 2016/3/24
 * Time: 8:17
 */
public class QueueTest {

    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    private static int count = 100000;
    private static int count2 = 2;

    private static CountDownLatch cd = new CountDownLatch(count2);

    public static void dothis() {
        for (int i = 0; i < count; i++) {
            queue.offer(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(4);
        QueueTest.dothis();

        for (int i = 0; i < count2; i++) {
            es.submit(new Poll());
        }

        cd.await();
        System.out.println("cost time " + (System.currentTimeMillis() - start) + "ms");
        es.shutdown();
    }

    static class Poll implements Runnable {
        @Override
        public void run() {
            while (queue.size() > 0) {
                //while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
            cd.countDown();
        }
    }
}
