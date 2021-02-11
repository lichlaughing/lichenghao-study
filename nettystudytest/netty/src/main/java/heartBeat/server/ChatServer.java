package heartBeat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 服务端
 */
public class ChatServer {

    private int port;

    public ChatServer() {
    }

    public ChatServer(int port) {
        this.port = port;
    }

    private void start() {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("stringEncoder", new StringEncoder());
                            pipeline.addLast("stringDecoder", new StringDecoder());
                            /**
                             * 心跳检测处理器,参数依次表示，多久没有读操作，多久没有写操作，多久没有读写操作
                             * 结果会传递给管道的下个处理器的userEventTriggered方法中处理
                             */
                            pipeline.addLast(new IdleStateHandler(2, 4, 8, TimeUnit.SECONDS));
                            pipeline.addLast("chatServerHandler", new ChatServerHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("聊天室启动成功！");
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatServer(6668).start();
    }
}
