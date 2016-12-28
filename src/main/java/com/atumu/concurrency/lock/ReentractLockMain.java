package com.atumu.concurrency.lock;

public class ReentractLockMain {

	public static void main(String[] args) {
		PrintQueue queue = new PrintQueue();

		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Job(queue));
		}

		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}
	}
}
