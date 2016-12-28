package com.atumu.concurrency.executor;

import java.util.Date;

public class PeriodTask implements Runnable{

	private String name;

	public PeriodTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s: Starting at : %s\n", name, new Date());
	}

}
