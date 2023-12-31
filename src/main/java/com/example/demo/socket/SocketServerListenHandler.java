package com.example.demo.socket;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerListenHandler {

    //A server socket waits for requests to come in over the network.
    //这是一个不断等待获取网络传入请求的服务端Socket
    private ServerSocket serverSocket;

    /**
     * 构造方法
     * @param port 端口
     */
    public SocketServerListenHandler(String hostname,int port){
        try{
            //serverSocket = new ServerSocket(port);
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));
            System.out.println("SocketServer服务端:"+serverSocket.getInetAddress()+":"+serverSocket.getLocalPort()+"");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 无限循环的监听客户端的连接
     * Listens for a connection to be made to this socket and accepts it.
     * 当有一个连接产生，将结束accept()方法的阻塞
     * The method blocks until a connection is made.
     */
    public void listenClientConnect(){
        int i=0;
        Socket clientConnectSocket = null;
        while(true){
            try {
                System.out.println(i+"号服务端监听开始。。。");
                clientConnectSocket = serverSocket.accept();
                InputStream inputStream = clientConnectSocket.getInputStream();
                byte[] data = new byte[100];

                clientConnectSocket.getOutputStream().write(data);



                //客户端地址信息：IP和端口号


                //SocketAddress clientAddress =clientConnectSocket.getRemoteSocketAddress();
                //System.out.println("监听到客户端连接。。。"+clientAddress);
                System.out.println(1111);
                //new SocketServerClientHandler(clientConnectSocket).start();
            } catch (Exception e) {
                System.out.println("服务端监听连接程序异常");
            }
            i++;
        }
    }
}
