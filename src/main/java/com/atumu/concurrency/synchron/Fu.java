package com.atumu.concurrency.synchron;

/**
 * User: zhanglin
 * Date: 2016/1/6
 * Time: 17:37
 */
public class Fu {

    public synchronized void test(String from) {
        System.out.println("Fu执行" + from);
        try {
            if ("zi".equalsIgnoreCase(from)) {
                Thread.sleep(5000);
            }
            System.out.println("Fu.test---------->" + from);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
