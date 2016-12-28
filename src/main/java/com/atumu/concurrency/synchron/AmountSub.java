package com.atumu.concurrency.synchron;

import com.atumu.entity.Account;

public class AmountSub implements Runnable{

	private Account account;
	public AmountSub(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for(int i=0;i<100;i++){
			account.subtractAmount(100);
			System.out.println("AmountSub.run()");
		}
	}

}
