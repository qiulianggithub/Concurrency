package com.atumu.concurrency.lock;

public class FileMock {

	private String[] contents;
	private int index;

	public FileMock(int size, int length) {
		contents = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuffer buffer = new StringBuffer(length);
			for (int j = 0; j < length; j++) {
				int indice = (int) Math.random() * 255;
				buffer.append((char) indice);
			}
			contents[i] = buffer.toString();
		}
		index = 0;
	}

	public boolean hasMoreLines() {
		return index < contents.length;
	}

	public String getLine() {
		if (this.hasMoreLines()) {
			System.out.println("Mock: " + (contents.length - index));
			return contents[index++];
		}
		return null;
	}
}
