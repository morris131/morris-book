---
title: BIO
date: 2019-04-17
categories: netty
tags: [BIO,netty]
---

# BIO

## server
[Server.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/bio/Server.java)
```java
package com.morris.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static int PORT = 8899;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("server is start on port: " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ServerHandler(socket)).start();
        }

    }

}
```
[ServerHandler.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/bio/ServerHandler.java)
```java
package com.morris.bio;

import java.io.*;
import java.net.Socket;
import java.nio.channels.NonReadableChannelException;

public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {

            // 读取数据
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            //while (true) {
                line = bufferedReader.readLine();

                System.out.println("receive message from client: " + line);

                // 发送数据
                printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                printWriter.println("hello client");
            //}


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(null != printWriter) {
                printWriter.close();
            }

            if(null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
```

## client
[Client.java](https://gitee.com/morris131/morris-book/blob/master/Java/netty/nettyDemo/src/main/java/com/morris/bio/Client.java)
```java
package com.morris.bio;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8899);

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 发送数据
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            printWriter.println("hello server");

            // 读取数据
            String line = null;
            while (null != (line = bufferedReader.readLine())) {
                System.out.println("receive message from server: " + line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != printWriter) {
                printWriter.close();
            }

            if(null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
```