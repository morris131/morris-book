package com.morris.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(1);

		AsynchronousSocketChannel asynchronousServerSocketChannel = AsynchronousSocketChannel.open();

		asynchronousServerSocketChannel.connect(new InetSocketAddress("127.0.0.1", 8899), asynchronousServerSocketChannel, new CompletionHandler<Void, AsynchronousSocketChannel>() {
			@Override
			public void completed(Void result, AsynchronousSocketChannel attachment) {

				ByteBuffer resultBuf = ByteBuffer.wrap("hello server".getBytes());
				attachment.write(resultBuf, attachment, new CompletionHandler<Integer, AsynchronousSocketChannel>() {
					@Override
					public void completed(Integer result, AsynchronousSocketChannel attachment) {

						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						attachment.read(byteBuffer, attachment, new CompletionHandler<Integer, AsynchronousSocketChannel>() {
							@Override
							public void completed(Integer result, AsynchronousSocketChannel attachment) {
								byteBuffer.flip();

								byte[] bytes = new byte[byteBuffer.remaining()];
								byteBuffer.get(bytes);

								String body = new String(bytes);

								System.out.println("receive from server: " + body);

								countDownLatch.countDown();
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
			public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

			}
		});
		countDownLatch.await();
    }
}
