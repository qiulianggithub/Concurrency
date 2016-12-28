package com.atumu.concurrency.thread;

public class PrimeMain {

	public static void main(String[] args) {
		Thread thread = new PrimeGenerator();
		thread.start();
		
		try {
			System.out.println(Thread.currentThread().getName() + ", sleep()前");
			thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + ", sleep()后");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
	}
	
	
}
