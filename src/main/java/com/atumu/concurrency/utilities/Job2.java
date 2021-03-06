package com.atumu.concurrency.utilities;

import java.util.Date;

public class Job2 implements Runnable{

	private PrintQueue2 printQueue;
	
	public Job2(PrintQueue2 printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		System.out.printf("%s: Going to print a job(%s)\n",Thread.currentThread().getName(),new Date());
		
		printQueue.printJob(new Object());
		
		System.out.printf("%s: The document has been printed(%s)\n",Thread.currentThread().getName(),new Date());
	}

	
}
