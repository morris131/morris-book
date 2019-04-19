---
title: http协议开发
date: 2019-04-19
categories: netty
tags: [netty]
---

# http协议开发

## server
[Server.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/http/Server.java)
```java
package com.morris.netty.protocol.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

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
                            ch.pipeline().addLast(new HttpRequestDecoder()); // 请求消息解码器
                            ch.pipeline().addLast(new HttpObjectAggregator(65536));// 目的是将多个消息转换为单一的request或者response对象
                            ch.pipeline().addLast(new HttpResponseEncoder());//响应解码器
                            ch.pipeline().addLast(new ServerHandler());// 业务逻辑
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", port).sync();
            System.out.println("HTTP服务器启动，网址是 : " + "http://127.0.0.1:" + port);
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
```

[ServerHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/http/ServerHandler.java)
```java
package com.morris.netty.protocol.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
        ByteBuf buffer = Unpooled.copiedBuffer("<h3>hello world</h3>", CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                    INTERNAL_SERVER_ERROR, Unpooled.copiedBuffer("Failure: " + INTERNAL_SERVER_ERROR.toString()
                    + "\r\n", CharsetUtil.UTF_8));
            response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
```

## client
浏览器输入： http://127.0.0.1:8899




