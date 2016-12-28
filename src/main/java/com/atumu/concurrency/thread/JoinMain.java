package com.atumu.concurrency.thread;

import com.atumu.entity.Person;

public class JoinMain {

	public static void main(String[] args) {
		try {
			Person person = new Person("zhangl");
			Thread thread = new JoinThread(person);
			
			thread.start();
			thread.join();
			
			System.out.println(person.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
