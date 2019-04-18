package com.morris.netty.frame.fail;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for(int i = 1 ; i <= 5; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(("hello"+i).getBytes()));
        }
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
