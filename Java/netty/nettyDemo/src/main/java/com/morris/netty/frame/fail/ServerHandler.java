package com.morris.netty.frame.fail;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            ByteBuf receiveByteBuf = (ByteBuf) msg;
            byte[] bytes = new byte[receiveByteBuf.readableBytes()];
            receiveByteBuf.readBytes(bytes);
            System.out.println("receive from client: " + new String(bytes));

            ctx.writeAndFlush(Unpooled.copiedBuffer(("hello client"+(++count)).getBytes()));

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
