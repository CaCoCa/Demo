package com.example.demo.netty.bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String body = null;
            while (true) {
                body = bufferedReader.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("body:" + body);
            }

            InputStream inputStream = socket.getInputStream();
            System.out.println(inputStream.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
