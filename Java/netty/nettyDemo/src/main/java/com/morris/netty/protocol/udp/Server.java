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
