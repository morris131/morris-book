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
