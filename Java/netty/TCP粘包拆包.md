---
title: TCP粘包拆包
date: 2019-04-18
categories: netty
tags: [netty]
---

# TCP粘包拆包
TCP粘包是在一次接收数据不能完全地体现一个完整的消息数据。

TCP通讯为何存在粘包呢？主要原因是TCP是以流的方式来处理数据，再加上网络上MTU的往往小于在应用处理的消息数据，所以就会引发一次接收的数据无法满足消息的需要，导致粘包的存在。

## 问题演示

### server
[Server.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/frame/fail/Server.java)
```java
package com.morris.netty.frame.fail;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    public static final int PORT = 8899;

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });

            // 启动 server.
            ChannelFuture f = b.bind(PORT).sync();

            System.out.println("server is start on port: " + PORT);

            // 等待socket关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
```
[ServerHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/frame/fail/ServerHandler.java)
```java
package com.morris.netty.frame.fail;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            ByteBuf receiveByteBuf = (ByteBuf) msg;
            byte[] bytes = new byte[receiveByteBuf.readableBytes()];
            receiveByteBuf.readBytes(bytes);
            System.out.println("receive from client: " + new String(bytes));

            ctx.writeAndFlush(Unpooled.copiedBuffer(("hello client"+(++count)).getBytes()));

        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}

```

### client
[Client.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/frame/fail/Client.java)
```java
package com.morris.netty.frame.fail;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            // 启动 server.
            ChannelFuture f = b.connect("127.0.0.1", 8899).sync();

            // 等待socket关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
```
[ClientHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/frame/fail/ClientHandler.java)
```java
package com.morris.netty.frame.fail;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for(int i = 1 ; i <= 5; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(("hello"+i).getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            ByteBuf receiveByteBuf = (ByteBuf) msg;
            byte[] bytes = new byte[receiveByteBuf.readableBytes()];
            receiveByteBuf.readBytes(bytes);
            System.out.println("receive from server: " + new String(bytes));

            ctx.close();

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
```

### 运行结果
server
```
server is start on port: 8899
receive from client: hello1hello2hello3hello4hello5
```
client
```
receive from server: hello client1
```

## 解决办法
1. 消息定长，报文大小固定长度，不够空格补全，发送和接收方遵循相同的约定，这样即使粘包了通过接收方编程实现获取定长报文也能区分。
2. 包尾添加特殊分隔符，例如每条报文结束都添加回车换行符（例如FTP协议）或者指定特殊字符作为报文分隔符，接收方通过特殊分隔符切分报文区分。
3. 将消息分为消息头和消息体，消息头中包含表示信息的总长度（或者消息体长度）的字段。
