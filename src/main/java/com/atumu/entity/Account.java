package com.atumu.entity;

import java.util.Date;

public class Account {

    private double balance;

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public synchronized void addAccount(double add){
		System.out.printf("Account.addAccount() - balance: %.0f - Time: %s\n",balance,new Date());
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tmp += add;
		balance = tmp;
	}
	
	public synchronized void subtractAmount(double sub) {
		System.out.printf("Account.subtractAmount() - balance: %.0f - Time: %s\n",balance,new Date());
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tmp -= sub;
		balance = tmp;
	}
}
