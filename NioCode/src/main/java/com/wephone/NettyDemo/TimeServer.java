package com.wephone.NettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {
    public void bind(int port) throws Exception{
        /*
         * 配置服务端的NIO线程
         * NioEventLoopGroup是线程组，包含了一组NIO线程
         * 这里配置两个 一个用于服务端接收客户端请求 另一个用于进行socketchannel的网络读写
         */
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            //启动辅助类 用于配置各种参数
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)//输入连接指示（对连接的请求）的最大队列长度被设置为 backlog 参数。如果队列满时收到连接指示，则拒绝该连接。
                    .childHandler(new ChildChannelMain());//绑定IO事件处理类
            //绑定端口 同步等待成功
            ChannelFuture future=b.bind(port).sync();
            System.out.println("时间服务器在"+port+"端口启动");
            //同步等待服务端监听端口关闭
            future.channel().closeFuture().sync();
            System.out.println("上一句会阻塞住");
        }finally {
            //释放资源退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
