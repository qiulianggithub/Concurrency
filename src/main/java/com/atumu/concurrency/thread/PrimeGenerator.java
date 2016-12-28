package com.atumu.concurrency.thread;

import java.io.IOException;
import java.io.PrintWriter;

public class PrimeGenerator extends Thread{

	@Override
	public void run() {
		long num  = 1L;
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("D:\\test.txt");
			
			while(true){
				if(isPrime(num)){
					pw.println("Number : "+ num +" is Prime Number");;
//					pw.println(isInterrupted());
//					pw.println(Thread.interrupted());
				}
				
//				if(isInterrupted()){
				if(Thread.interrupted()){
					pw.println("The Prime Generator has been Interrupted");
					pw.println(isInterrupted());
					pw.println(Thread.interrupted());
					return;
				}
				num++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
	}
	
	
	
	private boolean isPrime(long num){
		if(num <= 2){
			return true;
		}
		
		for(long i = 2; i < num; i++){
			if((num%i)==0){
				return false;
			}
		}
		return true;
	}
}
