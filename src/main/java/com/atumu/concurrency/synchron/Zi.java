package com.atumu.concurrency.synchron;

/**
 * User: zhanglin
 * Date: 2016/1/6
 * Time: 17:39
 */
public class Zi extends Fu {

    public synchronized void test(String from) {
        System.out.println("Zi执行" + from);
        super.test(from);
    }

    public static void main(String[] args) {
        final Zi zi = new Zi();
        final Fu fu = new Fu();

        new Thread(new Runnable() {
            @Override
            public void run() {
                zi.test("zi");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                fu.test("fu");
            }
        }).start();

    }
}
