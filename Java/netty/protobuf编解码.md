---
title: protobuf编解码
date: 2019-04-18
categories: netty
tags: [netty]
---

# protobuf编解码

## 准备
下载对应的[protoc](https://github.com/protocolbuffers/protobuf/releases)工具，用于生成实体类。

编写proto文件
[UserRequest.proto](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/resource/proto/UserRequest.proto)
```
syntax = "proto3";
option java_package = "com.morris.netty.serialize.protobuf";
option java_outer_classname = "UserRequestProto";

message UserRequest {
	int32 age = 1;
	string name = 2;
}
```

[UserResponseProto.proto](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/resource/proto/UserResponseProto.proto)
```
syntax = "proto3";
option java_package = "com.morris.netty.serialize.protobuf";
option java_outer_classname = "UserResponseProto";

message UserResponse {
	int32 code = 1;
	string message = 2;
}
```

用protoc.exe生成实体类，使用以下命令
```
protoc.exe --java_out=. UserRequest.proto
protoc.exe --java_out=. UserResponseProto.proto
```

## server
[Server.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/serialize/protobuf/Server.java)
```java
package com.morris.netty.serialize.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

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
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            ch.pipeline().addLast(new ProtobufDecoder(UserRequestProto.UserRequest.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
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
[ServerHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/serialize/protobuf/ServerHandler.java)
```java
package com.morris.netty.serialize.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            System.out.println("receive from client: " + msg);
            UserResponseProto.UserResponse response = UserResponseProto.UserResponse.newBuilder().setCode(200).setMessage("success").buildPartial();
            ctx.writeAndFlush(response);
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

## client
[Client.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/serialize/protobuf/Client.java)
```java
package com.morris.netty.serialize.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            ch.pipeline().addLast(new ProtobufDecoder(UserResponseProto.UserResponse.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
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
[ClientHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/serialize/protobuf/ClientHandler.java)
```java
package com.morris.netty.serialize.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserRequestProto.UserRequest request = UserRequestProto.UserRequest.newBuilder().setAge(18).setName("morris").buildPartial();
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            System.out.println("receive from server: " + msg);
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

### pojo
[UserRequestProto.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/serialize/protobuf/UserRequestProto.java)

[UserResponseProto.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/netty/serialize/protobuf/UserResponseProto.java)

### 运行结果
server
```
server is start on port: 8899
receive from client: age: 18
name: "morris"
```
client
```
receive from server: code: 200
message: "success"
```