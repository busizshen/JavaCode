package com.wephone.NettyChat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public  class ChatServer extends ChannelInitializer<SocketChannel> {

    public static Map<String,ChannelHandlerContext> channelContextMap=new ConcurrentHashMap();

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ChatServerHandler());
    }

    public static void main(String[] args) throws Exception {
        int port=9090;
//        this.bind(port);
        bind(port);
    }

    private static void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            //启动辅助类 用于配置各种参数
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)//输入连接指示（对连接的请求）的最大队列长度被设置为 backlog 参数。如果队列满时收到连接指示，则拒绝该连接。
                    .childHandler(new ChatServer());//绑定IO事件处理类
            //绑定端口 同步等待成功
            ChannelFuture future=b.bind(port).sync();
            System.out.println("聊天服务器在"+port+"端口启动");
            //同步等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        }finally {
            //释放资源退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
