package com.atumu.concurrency.synchron;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JoinTwo implements Runnable{

	@Override
	public void run() {
		System.out.println("JoinTwo.run.start("+new Date()+")");
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("JoinTwo.run.end("+new Date()+")");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
