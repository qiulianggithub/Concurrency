package com.atumu.concurrency.synchron;

import java.util.Date;

import com.atumu.entity.Account;

public class AccountMain {

	public static void main(String[] args) {
		Account account = new Account();
		account.setBalance(100000);
		
		AmountAdd add = new AmountAdd(account);
		Thread tAdd = new Thread(add);
		
		AmountSub sub = new AmountSub(account);
		Thread tSub = new Thread(sub);
		
		System.out.printf("Account : Initial(%s) Balance: %f\n", new Date(), account.getBalance());
		tAdd.start();
		tSub.start();
		
		try {
			tSub.join();
			tAdd.join();
			System.out.printf("Account : Last(%s) Balance: %f\n", new Date(),account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
