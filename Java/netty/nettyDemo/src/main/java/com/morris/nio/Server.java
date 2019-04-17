package com.morris.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    public static int PORT = 8899;

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false); // 设置为非阻塞方式

        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册监听的事件

        System.out.println("server is start on port: " + PORT);

        while (true) {

            selector.select(1000);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()) {
                SelectionKey key = selectionKeyIterator.next();
                selectionKeyIterator.remove();

                if(key.isValid()) {
                    if(key.isAcceptable()){
                        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
                        SocketChannel sc = ssChannel.accept();
                        sc.configureBlocking(false);
                        sc.register(key.selector(), SelectionKey.OP_READ);
                    }
                    if(key.isReadable()){
                        SocketChannel sc = (SocketChannel)key.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        int bytesRead = sc.read(buf);
                        if(bytesRead > 0){
                            buf.flip();

                            byte[] bytes = new byte[buf.remaining()];
                            buf.get(bytes);

                            String body = new String(bytes);

                            System.out.println("receive from client: " + body);

                            String response = "hello client";

                            bytes = response.getBytes();

                            buf = ByteBuffer.allocate(bytes.length);
                            buf.put(bytes);
                            buf.flip();
                            sc.write(buf);
                            key.cancel();
                            sc.close();
                        }
                    }
                }



            }

        }



    }

    public static void handleAccept(SelectionKey key) throws IOException{
        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ);
    }
    public static void handleRead(SelectionKey key) throws IOException{
        SocketChannel sc = (SocketChannel)key.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        long bytesRead = sc.read(buf);
        if(bytesRead>0){
            buf.flip();

            byte[] bytes = new byte[buf.remaining()];
            buf.get(bytes);

            String body = new String(bytes);

            System.out.println("receive from client: " + body);
        }
    }
    public static void handleWrite(SelectionKey key) throws IOException{
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while(buf.hasRemaining()){
            sc.write(buf);
        }
        buf.compact();
    }
}
