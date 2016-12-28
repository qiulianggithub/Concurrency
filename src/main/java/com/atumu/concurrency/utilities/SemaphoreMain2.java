package com.atumu.concurrency.utilities;

public class SemaphoreMain2 {

	public static void main(String[] args) {
		PrintQueue2 queue = new PrintQueue2();

		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job2(queue),"Thread" + i);
		}

		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
}
