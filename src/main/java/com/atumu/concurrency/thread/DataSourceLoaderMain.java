package com.atumu.concurrency.thread;

import java.util.Date;

public class DataSourceLoaderMain {

	public static void main(String[] args) {
		System.out.println(new Date());
		DataSourceLoader loader = new DataSourceLoader();
		Thread thread1 = new Thread(loader, "DataSourceThread");

		NetworkConnectionsLoader netLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(netLoader, "NetworkConnectionsThread");

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join(5*1000*10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Main: Configuration has been loaded: %s\n",new Date());
	}
}
