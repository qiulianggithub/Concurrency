package com.atumu.concurrency.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class SafeTaskMain {

	public static void main(String[] args) {
		SafeTask task = new SafeTask();
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(task);
			thread.start();

			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void test(){
		Random random1=new Random();
		Random random2=new Random();
		System.out.println(random1.nextInt());
		System.out.println(random2.nextInt());
	}
}
