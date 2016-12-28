package com.atumu.concurrency.thread;

import java.util.ArrayDeque;
import java.util.Deque;

import com.atumu.entity.Event;
import com.atumu.entity.Person;
import org.junit.Test;

public class DaemonMain {

	public static void main(String[] args) {
		Deque<Event> deque = new ArrayDeque<Event>();
		
		WriterTask writer = new WriterTask(deque);
		for(int i=0; i<3; i++){
			Thread thread = new Thread(writer);
			thread.start();
		}
		
		CleanerTask cleanerTask = new CleanerTask(deque);
		cleanerTask.start();
	}
	
	@Test
	public void test() {
		int count = 0;
		do {
			count++;
			System.out.println(count);
		} while (count < 5);
	}
	
	@Test
	public void test2() {
		Integer i = 20;
		Person person = new Person();
		person.setAge(i);
		i=24;
		System.out.println(person.getAge());
	}
}
