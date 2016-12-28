package com.atumu.concurrency.utilities;

public class SemaphoreMain {

	public static void main(String[] args) {
		PrintQueue queue = new PrintQueue();

		Thread thread[] = new Thread[100];
		for (int i = 0; i < 100; i++) {
			//1. thread[i] = new Thread(job, "Thread" + i);
			thread[i] = new Thread(new Job(queue),"Thread" + i);
		}

		for (int i = 0; i < 100; i++) {
			thread[i].start();
		}
	}
}
