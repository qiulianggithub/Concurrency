package com.atumu.concurrency.executor;

public class Result {

	private String name;
	private int value;
	/**
	 * @return name 
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return value 
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
