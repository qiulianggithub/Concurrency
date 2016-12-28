package com.atumu.concurrency.synchron;

import java.util.Date;

public class JoinMain2 {

	public static void main(String[] args) {
		
		System.out.println("Main start: "+new Date());
		Thread one = new Thread(new JoinOne());
		Thread two = new Thread(new JoinTwo());
		
		one.start();
		two.start();
		
		try {
			one.join();
			two.join();
			System.out.println("Main end: "+ new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
