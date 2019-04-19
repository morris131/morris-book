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
