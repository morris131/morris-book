package com.morris.netty.protocol.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
        ByteBuf buffer = Unpooled.copiedBuffer("<h3>hello world</h3>", CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                    INTERNAL_SERVER_ERROR, Unpooled.copiedBuffer("Failure: " + INTERNAL_SERVER_ERROR.toString()
                    + "\r\n", CharsetUtil.UTF_8));
            response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
