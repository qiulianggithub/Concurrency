package com.atumu.concurrency.thread;

import java.util.concurrent.TimeUnit;

public class MyThreadFactoryTask implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
