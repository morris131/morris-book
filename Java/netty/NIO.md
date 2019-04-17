---
title: NIO
date: 2019-04-17
categories: netty
tags: [NIO,netty]
---

# NIO 
NIO 是一种同步非阻塞的 IO 模型。同步是指线程不断轮询 IO 事件是否就绪，非阻塞是指线程在等待 IO 的时候，可以同时做其他任务。

在NIO中有几个核心对象需要掌握：缓冲区（Buffer）、通道（Channel）、选择器（Selector）。

## Buffer
Buffer其实就是一个数组，在NIO库中，所有数据都是用缓冲区处理的。在读取数据时，它是直接读到缓冲区中的； 在写入数据时，它也是写入到缓冲区中的；任何时候访问 NIO 中的数据，都是将它放到缓冲区中。而在面向流I/O系统中，所有数据都是直接写入或者直接将数据读取到Stream对象中。
   
所有的缓冲区类型都继承于抽象类Buffer，最常用的就是ByteBuffer。

## Channel
Channel是一个对象，通过它可以读取和写入数据，当然了所有数据都通过Buffer对象来处理。

## Selector
通道和缓冲区的机制，使得线程无需阻塞地等待IO事件的就绪，但是总是要有人来监管这些IO事件。这个工作就交给了selector来完成，这就是所谓的同步。

Selector允许单线程处理多个 Channel。如果你的应用打开了多个连接（通道），但每个连接的流量都很低，使用Selector就会很方便。

要使用Selector，得向Selector注册Channel，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪，这就是所说的轮询。一旦这个方法返回，线程就可以处理这些事件。

Selector中注册的感兴趣事件有：
- OP_ACCEPT
- OP_CONNECT 
- OP_READ 
- OP_WRITE

## server
[Server.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/nio/Server.java)
```java
package com.morris.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    public static int PORT = 8899;

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false); // 设置为非阻塞方式

        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册监听的事件

        System.out.println("server is start on port: " + PORT);

        while (true) {

            selector.select(1000);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()) {
                SelectionKey key = selectionKeyIterator.next();
                selectionKeyIterator.remove();

                if(key.isValid()) {
                    if(key.isAcceptable()){
                        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
                        SocketChannel sc = ssChannel.accept();
                        sc.configureBlocking(false);
                        sc.register(key.selector(), SelectionKey.OP_READ);
                    }
                    if(key.isReadable()){
                        SocketChannel sc = (SocketChannel)key.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        int bytesRead = sc.read(buf);
                        if(bytesRead > 0){
                            buf.flip();

                            byte[] bytes = new byte[buf.remaining()];
                            buf.get(bytes);

                            String body = new String(bytes);

                            System.out.println("receive from client: " + body);

                            String response = "hello client";

                            bytes = response.getBytes();

                            buf = ByteBuffer.allocate(bytes.length);
                            buf.put(bytes);
                            buf.flip();
                            sc.write(buf);
                            key.cancel();
                            sc.close();
                        }
                    }
                }
            }
        }
    }
}
```

## client
[Client.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/nio/Client.java)
```java
package com.morris.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {

    public static int PORT = 8899;

    private static volatile boolean stop = true;

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        SocketChannel serverSocketChannel = SocketChannel.open();

        serverSocketChannel.configureBlocking(false); // 设置为非阻塞方式

        boolean connect = serverSocketChannel.connect(new InetSocketAddress("127.0.0.1", PORT));

        if (connect) {
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册监听的事件
        } else {
            serverSocketChannel.register(selector, SelectionKey.OP_CONNECT); // 注册监听的事件
        }

        while (stop) {

            selector.select(1000);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()) {
                SelectionKey key = selectionKeyIterator.next();
                selectionKeyIterator.remove();

                if (key.isValid()) {
                    SocketChannel sc = (SocketChannel) key.channel();

                    if (key.isConnectable()) {

                        if (sc.finishConnect()) {
                            sc.register(selector, SelectionKey.OP_READ);

                            String response = "hello server";

                            byte[] bytes = response.getBytes();

                            ByteBuffer buf = ByteBuffer.allocate(bytes.length);
                            buf.put(bytes);
                            buf.flip();
                            sc.write(buf);
                        }

                    }

                    if (key.isReadable()) {
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        int bytesRead = sc.read(buf);
                        if (bytesRead > 0) {
                            buf.flip();

                            byte[] bytes = new byte[buf.remaining()];
                            buf.get(bytes);

                            String body = new String(bytes);

                            System.out.println("receive from server: " + body);

                            key.cancel();
                            sc.close();

                            stop = false;

                        }
                    }
                }
            }
        }
    }
}
```



