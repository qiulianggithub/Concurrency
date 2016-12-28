package com.atumu.concurrency.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedMain {

	public static void main(String[] args) {
		RejectedTaskController controller = new RejectedTaskController();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		executor.setRejectedExecutionHandler(controller);

		System.out.printf("Main: Starting.\n");
		for (int i = 0; i < 3; i++) {
			RejectedTask task = new RejectedTask("Task" + i);
			executor.submit(task);
		}

		System.out.printf("Main: Shutting down the Executor.\n");
		executor.shutdown();

		System.out.printf("Main: Sending another Task.\n");
		RejectedTask task = new RejectedTask("RejectedTask");
		executor.submit(task);

		System.out.println("Main: End");
		System.out.printf("Main: End.\n");
	}
}
