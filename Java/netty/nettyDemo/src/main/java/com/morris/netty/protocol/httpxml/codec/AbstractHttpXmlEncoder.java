package com.morris.netty.protocol.httpxml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.StringWriter;
import java.nio.charset.Charset;

import io.netty.util.CharsetUtil;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;

public abstract class AbstractHttpXmlEncoder<T> extends
        MessageToMessageEncoder<T> {
    IBindingFactory factory = null;
    StringWriter writer = null;

    protected ByteBuf encode0(ChannelHandlerContext ctx, Object body)
            throws Exception {
        factory = BindingDirectory.getFactory(body.getClass());
        writer = new StringWriter();
        IMarshallingContext mctx = factory.createMarshallingContext();
        mctx.setIndent(2);
        mctx.marshalDocument(body, CharsetUtil.UTF_8.name(), null, writer);
        String xmlStr = writer.toString();
        writer.close();
        writer = null;
        ByteBuf encodeBuf = Unpooled.copiedBuffer(xmlStr, CharsetUtil.UTF_8);
        return encodeBuf;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // 释放资源
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }

}
