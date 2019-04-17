package com.morris.netty.linebase;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            System.out.println("receive from client: " + msg);

            ctx.writeAndFlush(Unpooled.copiedBuffer("hello client1\n".getBytes()));
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello client2\n".getBytes()));
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello client3\n".getBytes()));

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
