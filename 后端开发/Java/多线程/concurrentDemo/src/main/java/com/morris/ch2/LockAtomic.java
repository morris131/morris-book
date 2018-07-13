package com.morris.ch2;

import java.util.concurrent.locks.ReentrantLock;

public class LockAtomic {
	
	public volatile int inc = 0;
	
	public ReentrantLock lock = new ReentrantLock();

    public void increase() {
    	try {			
    		lock.lock();
    		inc++;
		} finally {
			lock.unlock();
		}
    }

    public static void main(String[] args) {

        final LockAtomic test = new LockAtomic();

        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };

            }.start();
        }
         
        while(Thread.activeCount()>1) { //保证前面的线程都执行完
        	Thread.yield();
        }
        System.out.println(test.inc);

    }
}
