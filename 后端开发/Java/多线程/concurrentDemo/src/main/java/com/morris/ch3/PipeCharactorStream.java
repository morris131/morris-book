package com.morris.ch3;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipeCharactorStream {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		PipedWriter writer = new PipedWriter();
		PipedReader reader = new PipedReader();

		reader.connect(writer);

		new CharactorWriterThread(writer).start();

		Thread.sleep(1000);

		new CharactorReaderThread(reader).start();

	}

	static class CharactorWriterThread extends Thread {

		private PipedWriter writer;

		CharactorWriterThread(PipedWriter writer) {
			this.writer = writer;
		}

		@Override
		public void run() {

			System.out.print("put: ");
			try {
				for (int i = 0; i < 100; i++) {
					String str = i + "";
						writer.write(str);
						System.out.print(str);
				}
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
		}
	}

	static class CharactorReaderThread extends Thread {

		private PipedReader reader;

		CharactorReaderThread(PipedReader reader) {
			this.reader = reader;
		}

		@Override
		public void run() {
			char[] buffer = new char[1024];
			int len = 0;
			try {
				while (-1 != (len = reader.read(buffer))) {
					String data = new String(buffer, 0, len);
					System.out.print("read: " + data);
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
