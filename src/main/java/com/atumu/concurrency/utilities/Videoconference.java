package com.atumu.concurrency.utilities;

import java.util.concurrent.CountDownLatch;

public class Videoconference implements Runnable {

	private final CountDownLatch controller;

	public Videoconference(int num) {
		this.controller = new CountDownLatch(num);
	}

	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());

		try {
			controller.await();
			System.out.printf("VideoConference: All the participants have come\n");
			controller.await();
//			TimeUnit.SECONDS.sleep(5);
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void arrive(String name) {
		System.out.printf("%s has arrived. ", name);
		controller.countDown();
		System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
	}

}
