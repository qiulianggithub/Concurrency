package com.atumu.concurrency.utilities;

import java.util.Date;

public class Job implements Runnable{

	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		System.out.printf("%s: Going to print a job(%s)\n",Thread.currentThread().getName(),new Date());
		
		printQueue.printJob(new Object());
		
		System.out.printf("%s: The document has been printed(%s)\n",Thread.currentThread().getName(),new Date());
	}

	
}
