package com.atumu.concurrency.synchron;

import com.atumu.entity.Account;

public class AmountAdd implements Runnable{

	private Account account;
	public AmountAdd(Account account) {
		this.account = account;
	}
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			account.addAccount(100);
			System.out.println("AmountAdd.run()");
		}
	}

}
