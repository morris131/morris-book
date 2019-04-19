package com.morris.netty.protocol.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private static final String CR = System.getProperty("line.separator");

    @Override
    public void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {

        File file = new File(msg);

        if(!file.exists()) {
            ctx.write("file not found" + msg + CR);
            ctx.close();
            return;
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

        FileRegion fileRegion = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());

        ctx.write(fileRegion);
        ctx.writeAndFlush(CR);
        randomAccessFile.close();
    }

}
