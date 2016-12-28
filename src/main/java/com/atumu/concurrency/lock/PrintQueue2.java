package com.atumu.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue2 {

//    private Lock queueLock = new ReentrantLock(true);
    private Lock queueLock = new ReentrantLock(false);

    public void printJob(Object document) {

        queueLock.lock();

        try {

            Long duration = (long) (Math.random() * 10000);

            System.out.println(Thread.currentThread().getName() + ":=====first====  PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");

            Thread.sleep(duration);

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            queueLock.unlock();

        }

        queueLock.lock();

        try {

            Long duration = (long) (Math.random() * 10000);

            System.out.println(Thread.currentThread().getName() + ":=====second===== PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");

            Thread.sleep(duration);

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            queueLock.unlock();

        }

    }
}
