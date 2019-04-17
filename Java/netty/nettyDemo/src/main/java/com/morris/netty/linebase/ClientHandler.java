package com.morris.netty.linebase;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("aaaa\n".getBytes()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("bbbbbbb\n".getBytes()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("ccccccccccc\n".getBytes()));
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
