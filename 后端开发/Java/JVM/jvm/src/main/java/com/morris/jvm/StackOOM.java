package com.morris.jvm;

/**
 * VM args:-Xss200m
 * @author Morris
 *
 */
public class StackOOM {
	
	public void donotStop() {
		while(true) {
			
		}
	}
	
	public void startThread() {
		while (true) {
			new Thread(new Runnable() {
				public void run() {
					donotStop();
				}
			}).start();
		}
	}
	
	public static void main(String[] args) {
		new StackOOM().startThread();
	}

}
