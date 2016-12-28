package com.atumu.concurrency.synchron;

import com.atumu.entity.EventStorage;

public class Consumer implements Runnable{

	private EventStorage storage;
	
	
	public Consumer(EventStorage storage) {
		this.storage = storage;
	}


	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}

}
