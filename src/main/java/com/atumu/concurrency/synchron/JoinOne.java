package com.atumu.concurrency.synchron;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JoinOne implements Runnable{

	@Override
	public void run() {
		System.out.println("JoinOne.run.start("+new Date()+")");
		try {
			TimeUnit.SECONDS.sleep(5);
			System.out.println("JoinOne.run.end("+new Date()+")");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
