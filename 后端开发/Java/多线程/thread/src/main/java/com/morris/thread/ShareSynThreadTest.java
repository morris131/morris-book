package com.morris.thread;

public class ShareSynThreadTest {

	public static void main(String[] args) {
		ShareSynCountThread thread = new ShareSynCountThread();
		Thread a = new Thread(thread, "A");
		Thread b = new Thread(thread, "B");
		Thread c = new Thread(thread, "C");
		Thread d = new Thread(thread, "D");
		Thread e = new Thread(thread, "E");
		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
	}

}
