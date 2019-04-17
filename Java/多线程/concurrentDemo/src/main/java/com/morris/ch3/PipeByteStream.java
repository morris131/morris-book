package com.morris.ch3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeByteStream {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream();
		
		in.connect(out);
		
		new ByteOutputThread(out).start();
		
		Thread.sleep(1000);
		
		new ByteInputThread(in).start();
		
		
	}
	
	static class ByteOutputThread extends Thread {
		
		private PipedOutputStream out;
		
		ByteOutputThread(PipedOutputStream out) {
			this.out = out;
		}
		
		@Override
		public void run() {
			
			System.out.print("put: ");
			try {
				for(int i = 0; i < 100; i++) {
					String str = i + "";
						out.write(str.getBytes());
						System.out.print(str);
				}
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
		}
	}
	
	static class ByteInputThread extends Thread {
		
		private PipedInputStream in;
		
		ByteInputThread(PipedInputStream in) {
			this.in = in;
		}
		
		@Override
		public void run() {
			byte[] buffer = new byte[1024];
			int len = 0;
			try {
				while(-1 != (len = in.read(buffer))) {
					String data = new String(buffer, 0, len);
					System.out.print("read: " + data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
