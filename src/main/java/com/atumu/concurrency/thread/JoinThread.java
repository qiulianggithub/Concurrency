package com.atumu.concurrency.thread;

import com.atumu.entity.Person;

public class JoinThread extends Thread{

	private Person p;
	
	@Override
	public void run() {
		if(p != null){
			System.out.printf("%s : %s\r\n",this.getName(),p.getName());
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.setName("wuht");
		}
	}

	public JoinThread(Person p) {
		this.p = p;
	}
}
