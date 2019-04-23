---
title: websocket协议开发
date: 2019-04-22
categories: netty
tags: [netty]
---

# websocket协议开发

## server
[Server.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/websocket/Server.java)
```java
package com.morris.netty.protocol.websocket;

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
import io.netty.handler.stream.ChunkedWriteHandler;

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
                            ch.pipeline().addLast(new ChunkedWriteHandler());//目的是支持异步大文件传输（）
                            ch.pipeline().addLast(new ServerHandler());// 业务逻辑
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", port).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
```

[ServerHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/protocol/websocket/ServerHandler.java)
```java
package com.morris.netty.protocol.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    @Override
    public void messageReceived(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // 传统的HTTP接入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
        // WebSocket接入
        else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx,
                                   FullHttpRequest req) throws Exception {

        // 如果HTTP解码失败，返回HHTP异常
        if (!req.getDecoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1,
                    BAD_REQUEST));
            return;
        }

        // websocket服务器地址ws://localhost:8899/ws
        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:8899/ws", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx,
                                      WebSocketFrame frame) throws InterruptedException {

        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(),
                    (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.writeAndFlush(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }

        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();

        System.out.println(ctx.channel() + " receive " + request);

        while (true) {
            ctx.writeAndFlush(new TextWebSocketFrame("当前时间：" + LocalDateTime.now()));
            TimeUnit.SECONDS.sleep(1);
        }

    }

    private static void sendHttpResponse(ChannelHandlerContext ctx,
                                         FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),
                    CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            setContentLength(res, res.content().readableBytes());
        }

        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
```

## client
[websocket.html](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/resources/html/websocket.html)
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Socket</title>
    <script type="text/javascript">
        var websocket;

        //如果浏览器支持WebSocket
        if(window.WebSocket){
            websocket = new WebSocket("ws://localhost:8899/ws");  //获得WebSocket对象

            //当有消息过来的时候触发
            websocket.onmessage = function(event){
                var respMessage = document.getElementById("respMessage");
                respMessage.value = event.data;
            }

            //连接关闭的时候触发
            websocket.onclose = function(event){
                var respMessage = document.getElementById("respMessage");
                respMessage.value = respMessage.value + "\n断开连接";
            }

            //连接打开的时候触发
            websocket.onopen = function(event){
                var respMessage = document.getElementById("respMessage");
                respMessage.value = "建立连接";
            }
        }else{
            alert("浏览器不支持WebSocket");
        }

        function sendMsg(msg) { //发送消息
            if(window.WebSocket){
                if(websocket.readyState == WebSocket.OPEN) { //如果WebSocket是打开状态
                    websocket.send(msg); //send()发送消息
                }
            }else{
                return;
            }
        }
    </script>
</head>
<body>
<form onsubmit="return false">
    <textarea style="width: 300px; height: 200px;" name="message"></textarea>
    <input type="button" onclick="sendMsg(this.form.message.value)" value="发送"><br>
    <h3>信息</h3>
    <textarea style="width: 300px; height: 200px;" id="respMessage"></textarea>
    <input type="button" value="清空" onclick="javascript:document.getElementById('respMessage').value = ''">
</form>
</body>
</html>
```




