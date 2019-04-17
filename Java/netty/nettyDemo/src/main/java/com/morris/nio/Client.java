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

public class Client {

    public static int PORT = 8899;

    private static volatile boolean stop = true;

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        SocketChannel serverSocketChannel = SocketChannel.open();

        serverSocketChannel.configureBlocking(false); // 设置为非阻塞方式

        boolean connect = serverSocketChannel.connect(new InetSocketAddress("127.0.0.1", PORT));

        if(connect) {
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册监听的事件
        } else {
            serverSocketChannel.register(selector, SelectionKey.OP_CONNECT); // 注册监听的事件
        }


        while (stop) {

            selector.select(1000);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()) {
                SelectionKey key = selectionKeyIterator.next();
                selectionKeyIterator.remove();

                if(key.isValid()) {
                    SocketChannel sc = (SocketChannel)key.channel();

                    if(key.isConnectable()) {

                        if(sc.finishConnect()) {
                            sc.register(selector, SelectionKey.OP_READ);

                            String response = "hello server";

                            byte[] bytes = response.getBytes();

                            ByteBuffer buf = ByteBuffer.allocate(bytes.length);
                            buf.put(bytes);
                            buf.flip();
                            sc.write(buf);
                        }

                    }

                    if(key.isReadable()){
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        int bytesRead = sc.read(buf);
                        if(bytesRead > 0){
                            buf.flip();

                            byte[] bytes = new byte[buf.remaining()];
                            buf.get(bytes);

                            String body = new String(bytes);

                            System.out.println("receive from server: " + body);

                            key.cancel();
                            sc.close();

                            stop=false;

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
