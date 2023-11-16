package com.example.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

public class NettyClient {

    public static void main(String[] args) {
        // 线程组
        EventLoopGroup group = new NioEventLoopGroup(1);
        try {
            // 客户端启动类入口
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(group)
                    // 设置管道
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    // 管道初始化器
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // SocketChannel 的处理管道
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            // 添加处理器
                            channelPipeline
                                    // 自定义的客户端处理器
                                    .addLast(new NettyClientHandler());
                        }
                    });
            // connect() 发起异步连接，sync() 同步等待连接成功
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9000).sync();
            // 等待客户端连接关闭
            channelFuture.channel().closeFuture().sync();
            System.out.println("Client close...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            group.shutdownGracefully();
        }
    }

    static class NettyClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel active...");

            ctx.channel().write(Unpooled.copiedBuffer("Hello Netty Server!", StandardCharsets.UTF_8));
            ctx.channel().write(Unpooled.copiedBuffer("Hello World!!!", StandardCharsets.UTF_8));
            ctx.channel().flush();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("channel read...");
            ByteBuf resBuf = (ByteBuf) msg;
            byte[] resBytes = new byte[resBuf.readableBytes()];
            resBuf.readBytes(resBytes);
            System.out.println("Response data: " + new String(resBytes, StandardCharsets.UTF_8));

            // 关闭通道
            ctx.channel().close();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel read complete...");
            ctx.flush();
        }
    }
}

