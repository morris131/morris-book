---
title: http协议开发
date: 2019-04-19
categories: netty
tags: [netty]
---

# http协议开发

## server
[Server.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/file/Server.java)
```java
package com.morris.netty.protocol.file;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class Server {

    private static final int port = 8899;

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                            ch.pipeline().addLast(new LineBasedFrameDecoder(65536));
                            ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", port).sync();
            System.out.println("file server is start on:" + port);
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
```

[ServerHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/file/ServerHandler.java)
```java
package com.morris.netty.protocol.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private static final String CR = System.getProperty("line.separator");

    @Override
    public void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {

        File file = new File(msg);

        if(!file.exists()) {
            ctx.writeAndFlush("file not found" + msg + CR);
            ctx.close();
            return;
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

        FileRegion fileRegion = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());

        ctx.write(fileRegion);
        ctx.writeAndFlush(CR);
        randomAccessFile.close();
    }

}
```

## client
命令行输入：telnet 127.0.0.1 8899 然后输入文件的路径




