---
title: udp协议开发
date: 2019-04-22
categories: netty
tags: [netty]
---

# udp协议开发

## server
[Server.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/udp/Server.java)
```java
package com.morris.netty.protocol.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class Server {

    private static final int port = 8899;

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ServerHandler());

            bootstrap.bind(port).sync().channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }

    }
}
```

[ServerHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/udp/ServerHandler.java)
```java
package com.morris.netty.protocol.udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {

        System.out.println(datagramPacket.content().toString(CharsetUtil.UTF_8));

        channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("hello client", CharsetUtil.UTF_8), datagramPacket.sender()));

    }
}
```

## client
[Client.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/udp/Client.java)
```java
package com.morris.netty.protocol.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ClientHandler());

            Channel channel = bootstrap.bind(0).channel();

            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("hello server", CharsetUtil.UTF_8), new InetSocketAddress("255.255.255.255", 8899))).sync();

            if(!channel.closeFuture().await(30*1000)) {
                System.err.println("查询超时");
            }
        } finally {
            group.shutdownGracefully();
        }

    }
}
```

[ClientHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/udp/ClientHandler.java)
```java
package com.morris.netty.protocol.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class ClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {

        System.out.println(datagramPacket.content().toString(CharsetUtil.UTF_8));

        channelHandlerContext.close();
    }
}
```




