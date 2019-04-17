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
