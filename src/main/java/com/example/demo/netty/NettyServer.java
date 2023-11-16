package com.example.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;

public class NettyServer {

    public int port;

    NettyServer(int port) {
        this.port = port;
    }

    /**
     * 启动 Netty Server
     */
    public void start() {
        // 负责和客户端建立网络连接的线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 负责处理网络IO请求读取和处理的线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup(32);

        try {
            // Netty网络服务器（服务端启动类）
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    // 设置线程组
                    .group(bossGroup, workerGroup)
                    // 网络通信通道，负责监听指定的端口
                    .channel(NioServerSocketChannel.class)
                    // 网络通道处理器初始化
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // SocketChannel 的处理管道
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            // 添加一些处理器
                            channelPipeline
                                    // 自定义的服务端处理器
                                    .addLast(new NettyServerHandler());
                        }
                    })
                    // 设置全连接队列大小
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 保持网络连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
            ;

            // 绑定要监听的端口，sync() 同步等待完成
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    static class NettyServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel active...");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("channel read...");

            // 读数据并处理请求
            ByteBuf reqBuf = (ByteBuf) msg;
            byte[] reqBytes = new byte[reqBuf.readableBytes()];
            reqBuf.readBytes(reqBytes);
            System.out.println("Request data: " + new String(reqBytes, StandardCharsets.UTF_8));

            // 响应客户端请求
            System.out.println("channel writing...");
            ctx.channel().write(Unpooled.copiedBuffer("Hello Netty Client!", StandardCharsets.UTF_8));
            ctx.channel().write(Unpooled.copiedBuffer("Hello World!!!", StandardCharsets.UTF_8));

            ctx.channel().flush();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel read complete...");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("channel exception...");
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static void main(String[] args) {
        // 启动 Netty Server
        NettyServer nettyServer = new NettyServer(9000);
        nettyServer.start();
    }

}
