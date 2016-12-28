package com.atumu.concurrency.lock;

public class ReadWriterLockMain {

	public static void main(String[] args) {
		PricesInfo info = new PricesInfo();

		Reader readers[] = new Reader[5];
		Thread threadsReader[] = new Thread[5];
		for (int i = 0; i < 5; i++) {
			readers[i] = new Reader(info);
			threadsReader[i] = new Thread(readers[i]);
		}

		Writer writer = new Writer(info);
		Thread threadWriter = new Thread(writer);
		
		
		for (int i = 0; i < 5; i++) {
			threadsReader[i].start();
		}
		threadWriter.start();

	}
}
