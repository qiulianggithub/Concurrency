package com.atumu.concurrency.thread;

import java.io.File;

public class FileSearch implements Runnable{

	private String initPath;
	private String fileName;
	
	
	
	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}



	@Override
	public void run() {
		File file = new File(initPath);
		if(file.isDirectory()){
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s: The search has been interrupted",Thread.currentThread().getName());
			}
		}
	}
	
	private void directoryProcess(File file) throws InterruptedException {
		File[] files = file.listFiles();
		if(files != null){
			for(int i=0; i<files.length;i++){
				if(files[i].isDirectory()){
					directoryProcess(files[i]);
				}else{
					fileProcess(files[i]);
				}
			}
		}
		if(Thread.interrupted()){
			throw new InterruptedException();
		}
	}



	private void fileProcess(File file) throws InterruptedException {
		if(file.getName().equals(fileName)){
			System.out.printf("%s : %s", 
					Thread.currentThread().getName(),
					file.getAbsolutePath());
		}
		if(Thread.interrupted()){
			throw new InterruptedException();
		}
	}
}
