package com.atumu.concurrency.executor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {

	private String name;

	public UserValidator(String name) {
		this.name = name;
	}

	public boolean validate(String name, String password) {
		Random random = new Random();

		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			return false;
		}
		boolean nextBoolean = random.nextBoolean();
		System.out.printf("Validator %s: %s\n",this.name ,nextBoolean);
		return nextBoolean;
	}

	/**
	 * @return name 
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
