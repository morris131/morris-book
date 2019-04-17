package com.morris.jvm.oom;

public class NativeMethodStackOOM {
	
	public static void main(String[] args) {
		
		while(true) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
					}
					
				}
			}).start();
		}
	}

}
