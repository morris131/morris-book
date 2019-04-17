package com.morris.netty.paste;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("aaaa".getBytes()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("bbbbbbb".getBytes()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("ccccccccccc".getBytes()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            ByteBuf receiveByteBuf = (ByteBuf) msg;
            byte[] bytes = new byte[receiveByteBuf.readableBytes()];
            receiveByteBuf.readBytes(bytes);
            System.out.println("receive from server: " + new String(bytes));

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
