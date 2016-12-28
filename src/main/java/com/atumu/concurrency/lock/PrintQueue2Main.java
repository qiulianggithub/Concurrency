package com.atumu.concurrency.lock;

/**
 * Created by 邱亮 on 2016/11/21.
 */
public class PrintQueue2Main {

    public static void main(String[] args) {
        Thread [] thread = new Thread[10];
        PrintQueue2 queue = new PrintQueue2();
        for (int i=0; i<10; i++){
            thread[i] = new Thread(new Job2(queue));
        }
        for (int i=0; i<10; i++){
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
