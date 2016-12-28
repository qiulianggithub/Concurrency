package com.atumu.concurrency.utilities;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.atumu.entity.Results;

public class Searcher implements Runnable {

	private int firstRow;
	private int lastRow;
	private MatrixMock mock;
	private Results results;
	private int number;
	private final CyclicBarrier barrier;

	public Searcher(int firstRow, int lastRow, MatrixMock mock,
			Results results, int number, CyclicBarrier barrier) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		int counter;
		System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);

		for (int i = firstRow; i < lastRow; i++) {
			int row[] = mock.getRow(i);
			counter = 0;
			for (int j = 0; j < row.length; j++) {
				if (row[j] == number) {
					counter++;
				}
			}
			results.setData(i, counter);
		}

		System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());

		try {
			barrier.await();// 当单个searcher到此会被置为wait，直到barrier的线程全部处于wait状态，它会执行grouper线程
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		} 
	}

}
