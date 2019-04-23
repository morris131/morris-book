package com.morris.netty.protocol.httpxml.client;

import com.morris.netty.protocol.httpxml.codec.HttpXmlRequest;
import com.morris.netty.protocol.httpxml.codec.HttpXmlResponse;
import com.morris.netty.protocol.httpxml.pojo.UserRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HttpXmlClientHandle extends
        SimpleChannelInboundHandler<HttpXmlResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("morris");
        userRequest.setAge(18);
        HttpXmlRequest request = new HttpXmlRequest(null, userRequest);
        ctx.writeAndFlush(request);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
        System.out.println("The client receive response of http header is : " + msg.getHttpResponse().headers().names());
        System.out.println("The client receive response of http body is : " + msg.getResult());
    }
}
