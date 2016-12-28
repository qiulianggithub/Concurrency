package com.atumu.concurrency.thread;

public class UnCaughtExceptionMain {

	public static void main(String[] args) {
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
		GroupTask task = new GroupTask();

		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(threadGroup, task);
			t.start();
		}
	}
}
