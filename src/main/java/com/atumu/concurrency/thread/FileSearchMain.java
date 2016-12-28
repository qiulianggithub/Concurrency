package com.atumu.concurrency.thread;

import java.util.concurrent.TimeUnit;

public class FileSearchMain {

	public static void main(String[] args) {
		FileSearch search = new FileSearch("D:\\工作记录", "瑞东接口.txt");
        Thread thread = new Thread(search);
        thread.start();
		
		try {
			TimeUnit.MILLISECONDS.sleep(500000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
	}
}
