package com.atumu.concurrency.synchron;

import com.atumu.entity.EventStorage;

public class PCMain {

	public static void main(String[] args) {
		
		EventStorage storage = new EventStorage();
		
		Thread producer = new Thread(new Producer(storage));
		Thread consumer = new Thread(new Consumer(storage));
		
		producer.start();
		consumer.start();
	}
}
