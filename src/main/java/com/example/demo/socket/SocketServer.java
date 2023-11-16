package com.example.demo.socket;

public class SocketServer {

    private static final int PORT = 8400;//监听的端口号
    private static final String HostName="127.0.0.1";//服务器IP

    public static void main(String[] args) throws Exception{
        SocketServerListenHandler socketServerListenHandler = new SocketServerListenHandler(HostName,PORT);
        socketServerListenHandler.listenClientConnect();
    }
}
