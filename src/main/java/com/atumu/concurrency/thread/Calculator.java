package com.atumu.concurrency.thread;

public class Calculator implements Runnable{

	private int num;

	public Calculator(int num) {
		System.out.printf("%s: %d\n",
				Thread.currentThread().getName(),
				num);
//		System.out.println("Main-Thred-"+num);
		this.num = num;
	}

	@Override
	public void run() {
		for(int i=0;i<10;i++){
//			if(num == 6){
//				System.exit(0);
//			}
			System.out.printf("%s: %d * %d = %d\n",
					Thread.currentThread().getName()+","+Thread.currentThread().getId(),
					num,
					i,
					i*num);
		}
	}
	
}
