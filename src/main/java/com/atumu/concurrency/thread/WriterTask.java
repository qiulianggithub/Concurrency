package com.atumu.concurrency.thread;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

import com.atumu.entity.Event;

public class WriterTask implements Runnable {

	private Deque<Event> deque;
	public WriterTask(Deque<Event> deque) {
		this.deque = deque;
	}

	@Override
	public void run() {
		for(int i=1;i<100;i++){
			Event event = new Event();
			Date date = new Date();
			event.setDate(date);
			event.setEvent(String.format("WriterTask-%s生成了一个Event(date:%s)!,", Thread.currentThread().getId(),date+"~~~~"+date.getTime()));
			
			deque.addFirst(event);
			System.out.println("deque.size:"+deque.size());
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}

	
}
