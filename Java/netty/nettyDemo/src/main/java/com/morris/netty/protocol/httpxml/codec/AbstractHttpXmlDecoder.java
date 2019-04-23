package com.morris.netty.protocol.httpxml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.io.StringReader;
import java.nio.charset.Charset;

import io.netty.util.CharsetUtil;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;

public abstract class AbstractHttpXmlDecoder<T> extends
        MessageToMessageDecoder<T> {

    private IBindingFactory factory;
    private StringReader reader;
    private Class<?> clazz;

    protected AbstractHttpXmlDecoder(Class<?> clazz) {
        this.clazz = clazz;
    }

    protected Object decode0(ChannelHandlerContext arg0, ByteBuf body)
            throws Exception {
        factory = BindingDirectory.getFactory(clazz);
        String content = body.toString(CharsetUtil.UTF_8);
        reader = new StringReader(content);
        IUnmarshallingContext uctx = factory.createUnmarshallingContext();
        Object result = uctx.unmarshalDocument(reader);
        reader.close();
        reader = null;
        return result;
    }

    @Skip
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // 释放资源
        if (reader != null) {
            reader.close();
            reader = null;
        }
    }
}
