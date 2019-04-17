package com.morris.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class Server {

	public static final int PORT = 8899;

	public static void main(String[] args) throws IOException, InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(1);

		AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();

		asynchronousServerSocketChannel.bind(new InetSocketAddress(PORT));

		asynchronousServerSocketChannel.accept(asynchronousServerSocketChannel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
			@Override
			public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {

				attachment.accept(attachment, this);

				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				result.read(byteBuffer, result , new CompletionHandler<Integer, AsynchronousSocketChannel>() {
					@Override
					public void completed(Integer result, AsynchronousSocketChannel attachment) {
						byteBuffer.flip();

						// read
						byte[] bytes = new byte[byteBuffer.remaining()];
						byteBuffer.get(bytes);
						System.out.println("receive from client: " + new String(bytes));

						// write
						ByteBuffer resultBuf = ByteBuffer.wrap("hello client".getBytes());
						attachment.write(resultBuf, attachment, new CompletionHandler<Integer, AsynchronousSocketChannel>() {
							@Override
							public void completed(Integer result, AsynchronousSocketChannel attachment) {
							}

							@Override
							public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

							}
						});

					}

					@Override
					public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

					}
				});
			}

			@Override
			public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {

			}
		});

		System.out.println("server is start on port: " + PORT);

		countDownLatch.await();

    }
}
