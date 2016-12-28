package com.atumu.concurrency.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourceLoader implements Runnable {

	@Override
	public void run() {
		System.out.printf("%s Beginning data sources loading: %s\n",
				Thread.currentThread().getName(),
				new Date());

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s Data sources loading has finished: %s\n",
				Thread.currentThread().getName(),
				new Date());
	}

}
