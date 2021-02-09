package nettyServerClient;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashSet;
import java.util.Set;

/**
 * 服务
 */
public class Server {

    private static final int PORT = 6666;

    public static Set<SocketChannel> channelList = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = null;
        NioEventLoopGroup workerGroup = null;
        try {
            // 负责客户端连接的工作组
            bossGroup = new NioEventLoopGroup();
            // 负责处理任务的工作组
            workerGroup = new NioEventLoopGroup();
            // 服务端配置
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            channelList.add(socketChannel);
                            socketChannel.pipeline().addLast(new ServerHandler());
                            System.out.println(channelList);
                        }
                    });

            // 绑定端口
            ChannelFuture channelFuture = bootstrap.bind(PORT).sync();
            channelFuture.addListener(future -> {
                if(future.isSuccess()){
                    System.out.println("端口监听成功");
                }
            });
            // 监听通道关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
