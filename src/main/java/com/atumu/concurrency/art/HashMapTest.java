package com.atumu.concurrency.art;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试HashMap多线程相关
 * User: zhanglin
 * Date: 2016/3/23
 * Time: 9:46
 */
public class HashMapTest {


    /**
     * 1. 为什么要用HashMap(数组+链表+Hash)？
     * 数组：充当索引
     * 链表：处理碰撞
     * Hash：就是根据某个算法将一系列目标对象转换成地址，当要获取某个元素的时候，只需要将目标对象做相应的运算获得地址，直接获取。
     * <p/>
     * <p/>
     * 2. 死锁
     * put方法是非相称安全的，并发情况下resize数组时可能出现问题。
     *
     * @throws InterruptedException
     */
    @Test
    public void testHashMap() throws InterruptedException {
        final Map<String, Object> hp = new HashMap<>(4);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            hp.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        });

        thread.start();
        thread.join();
    }


    private volatile ConcurrentHashMap<String, AtomicLong> chp = new ConcurrentHashMap();

    @Test
    public void testConcurrentHashMap() {
        final String[] strs = {"a", "b", "c", "d"};
        final Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increse(strs[random.nextInt(4)]);
                    //System.out.println(strs[random.nextInt(4)]);
                }
            }).start();
        }

        try {
            System.out.println("设置Thread.sleep延时2s，等等计算吧...");
            Thread.sleep(2000);
            System.out.println(chp.toString());
            AtomicLong count = new AtomicLong(0);
            for (AtomicLong l : chp.values()) {
                count.addAndGet(l.get());
            }
            System.out.printf("count: %s", count.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.java.util.concurrent.atomic包
     * 方便程序员在多线程环境下，无锁的进行原子操作
     *
     * @param word
     * @return
     */
    private long increse(String word) {
        AtomicLong num = chp.get(word);
        if (num == null) {
            AtomicLong newNum = new AtomicLong(0);
            num = chp.putIfAbsent(word, newNum);
            if (num == null) {
                num = newNum;
            }
        }
        return num.getAndIncrement();
    }


    private volatile Map<String, Long> map = new HashMap();

    /**
     * HashMap在
     */
    @Test
    public void testHashMap2() {
        final String[] strs = {"a", "b", "c", "d"};
        final Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increse2(strs[random.nextInt(4)]);
                    //System.out.println(strs[random.nextInt(4)]);
                }
            }).start();
        }

        try {
            System.out.println("设置Thread.sleep延时2s，等等计算吧...");
            Thread.sleep(2000);
            System.out.println(map.toString());
            AtomicLong count = new AtomicLong(0);
            for (Long l : map.values()) {
                count.addAndGet(l);
            }
            System.out.printf("count: %s", count.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param word
     * @return
     */
    private long increse2(String word) {
        Long num = map.get(word);
        if (num == null) {
            map.put(word, 1L);
            if (num == null) {
                num = 1L;
            }
        } else {
            num++;
            map.put(word, num);
        }
        return num;
    }

    private volatile Map<String, AtomicLong> map2 = new HashMap();

    /**
     * HashMap在
     */
    @Test
    public void testHashMap3() {
        final String[] strs = {"a", "b", "c", "d"};
        final Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increse3(strs[random.nextInt(4)]);
                    //System.out.println(strs[random.nextInt(4)]);
                }
            }).start();
        }

        try {
            System.out.println("设置Thread.sleep延时2s，等等计算吧...");
            Thread.sleep(2000);
            System.out.println(map2.toString());
            AtomicLong count = new AtomicLong(0);
            for (AtomicLong l : map2.values()) {
                count.addAndGet(l.get());
            }
            System.out.printf("count: %s", count.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. 这个设计没不能说明HashMap的线程安全问题，但可以
     * @param word
     * @return
     */
    private long increse3(String word) {
        AtomicLong num = map2.get(word);
        if (num == null) {
            AtomicLong newNum = new AtomicLong(0);
            num = map2.put(word, newNum);
            if (num == null) {
                num = newNum;
            }
        }
        return num.getAndIncrement();
    }
}
