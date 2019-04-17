package com.morris.netty.delimiterbase;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter {

    int count = 1;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            System.out.println("receive from client: " + msg);

            ctx.writeAndFlush(Unpooled.copiedBuffer("hello client$_".getBytes()));
            //ctx.writeAndFlush(Unpooled.copiedBuffer("hello client2$_".getBytes()));
            //ctx.writeAndFlush(Unpooled.copiedBuffer("hello client3$_".getBytes()));

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
