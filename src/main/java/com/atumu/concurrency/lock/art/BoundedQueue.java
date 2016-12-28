package com.atumu.concurrency.lock.art;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界队列
 * User: zhanglin
 * Date: 2016/3/20
 * Time: 12:44
 */
public class BoundedQueue<T> {

    private Object[] items;
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    /**
     * 添加一个元素，如果数组满，则添加线程进入等待状态，知道有空位。
     *
     * @param t
     * @throws InterruptedException
     */
    public void add(T t) throws InterruptedException {
        O o = (O) t;
        lock.lock();
        try {
            long b = System.currentTimeMillis();
            while (count == items.length) {
                System.out.printf("满了\n");
                notFull.await();
            }
            long m = System.currentTimeMillis() - b;
            System.out.printf("m: %s\n", m);
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            System.out.printf("queue count: %s, addIndex: %s, O.num:%s; \n", count, addIndex, o.getNum());
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 由头部删除一个元素，如果数组空，则删除线程进入等待状态，知道有新的添加元素
     *
     * @return
     * @throws InterruptedException
     */
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            long b = System.currentTimeMillis();
            while (count == 0) {
                System.out.printf("空了\n");
                notEmpty.await();
            }
            long m = System.currentTimeMillis() - b;
            System.out.printf("m: %s\n", m);
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;

            System.out.printf("queue count: %s, removeIndex: %s, O.num:%s; \n", count, removeIndex, ((O) x).getNum());
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return count;
    }


    /**
     * 边界
     *
     * @param args
     */
    public static void main(String[] args) {
        final BoundedQueue<O> queue = new BoundedQueue<>(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    try {
                        queue.add(new O(i));
                        //System.out.printf("queue added %s \n", i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 15; i < 30; i++) {
                    try {
                        queue.add(new O(i));
                        //System.out.printf("queue added %s \n", i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
            System.out.printf("------------------after 1 second-----------------------\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        queue.remove();
                        //System.out.printf("queue remove %s \n", i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.printf("\n\nqueue size: %s\n", queue.size());
            }
        }).start();
    }

    static class O {
        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public O(int num) {
            this.num = num;
        }
    }

}
