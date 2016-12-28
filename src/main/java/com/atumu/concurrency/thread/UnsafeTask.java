package com.atumu.concurrency.thread;

import java.util.concurrent.TimeUnit;

public class UnsafeTask implements Runnable{

//	private Date startDate;

    private Long i=0l;//线程共享变量
	
	@Override
	public void run() {
        i=Thread.currentThread().getId();
        System.out.println("current thread "+Thread.currentThread().getId()+" start i is :"+i);
//		startDate = new Date();
//		System.out.printf("Starting Thread: %s : %s\n",Thread.currentThread().getId(),startDate);
		
		try {
			TimeUnit.SECONDS.sleep((long) Math.rint(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("current thread "+Thread.currentThread().getId()+" end i is :"+i);
//		System.out.printf("Thread Finished: %s : %s\n",Thread.currentThread().getId(),startDate);
	}
	
	

}
