package com.morris.thread;

public class Run {
	
	public static void main(String[] args) {
		
		CountOperate countOperate = new CountOperate();
		
		Thread thread = new Thread(countOperate);
		
		
		thread.start();
		thread.setName("A");
		
	}

}
