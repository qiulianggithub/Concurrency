package com.atumu.concurrency.thread;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class CalculatorMain {

	public static void main(String[] args) {
//		for (int num = 0; num < 10; num++) {
//			Calculator cal = new Calculator(num);
//			Thread thread = new Thread(cal);
//			thread.start();
//		}
		
		Thread[] threads = new Thread[10];
		Thread.State[] status = new Thread.State[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if(i%2 == 0){
				threads[i].setPriority(Thread.MAX_PRIORITY);
			}else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}

		try {
			FileWriter fw = new FileWriter("D:\\test.txt");
			PrintWriter pw = new PrintWriter(fw);

			for (int i = 0; i < 10; i++) {
				pw.println("Main : Status of Thread " + i + " : " +threads[i].getState());
				status[i]=threads[i].getState();
			}

			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}

			boolean finish=false;
			while(!finish){
				for(int i = 0; i < 10; i++){
					if (threads[i].getState()!= status[i]) {
						writeThreadInfo(pw,threads[i],status[i]);
						status[i]=threads[i].getState();
					}
				}
				finish = true;
				for(int i = 0; i < 10; i++){
					finish = finish && (threads[i].getState() == State.TERMINATED);
				}
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main : Id %d - %s\r\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\r\n", thread.getPriority());
		pw.printf("Main : Old State: %s\r\n", state);
		pw.printf("Main : New State: %s\r\n", thread.getState());
		pw.printf("Main : ************************************\r\n");
	}
}
