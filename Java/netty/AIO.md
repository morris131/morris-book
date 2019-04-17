---
title: AIO
date: 2019-04-17
categories: netty
tags: [AIO,netty]
---

# AIO

与NIO不同，当进行读写操作时，只须直接调用API的read或write方法即可。这两种方法均为异步的，对于读操作而言，当有流可读取时，操作系统会将可读的流传入read方法的缓冲区，并通知应用程序；对于写操作而言，当操作系统将write方法传递的流写入完毕时，操作系统主动通知应用程序。  即可以理解为，read/write方法都是异步的，完成后会主动调用回调函数。

## server

```java
package com.morris.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();

		asynchronousServerSocketChannel.bind(new InetSocketAddress(8899));

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

		System.out.println("server is start on port: 8899");

		countDownLatch.await();

    }
}
```

## client
```java
package com.morris.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
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
```


