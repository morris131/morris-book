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
