package com.morris.fakebio;

import com.morris.bio.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FakeBIOServer {

    public static int PORT = 8899;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("server is start on port: " + PORT);
        
        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 100, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));

        while (true) {
            Socket socket = serverSocket.accept();
            executorService.execute(new ServerHandler(socket));
        }

    }

}
