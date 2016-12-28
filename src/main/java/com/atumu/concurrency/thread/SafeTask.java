package com.atumu.concurrency.thread;

import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable{

    	private static ThreadLocal<Long> i = new ThreadLocal<Long>(){
		protected Long initialValue() {
			return Thread.currentThread().getId();
		};
	};

    @Override
    public void run() {
        System.out.println("current thread "+Thread.currentThread().getId()+" start i is :"+i.get());
//		startDate = new Date();
//		System.out.printf("Starting Thread: %s : %s\n",Thread.currentThread().getId(),startDate);

        try {
            TimeUnit.SECONDS.sleep((long) Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("current thread "+Thread.currentThread().getId()+" end i is :"+i.get());
//		System.out.printf("Thread Finished: %s : %s\n",Thread.currentThread().getId(),startDate);
    }

//	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>(){
//		protected Date initialValue() {
//			return new Date();
//		};
//	};
	
//	@Override
//	public void run() {
//		System.out.printf("Starting Thread: %s : %s\n",Thread.currentThread().getId(),startDate.get());
//
//		try {
//			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		System.out.printf("Thread Finished: %s : %s\n",Thread.currentThread().getId(),startDate.get());
//	}

}
