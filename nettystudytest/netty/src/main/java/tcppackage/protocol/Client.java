package tcppackage.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import tcppackage.protocol.model.MessageDecoder;
import tcppackage.protocol.model.MessageEncoder;

/*
演示TCP粘包拆包客户端
 */
public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6666;

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 自定义协议编码器
                            pipeline.addLast(new MessageEncoder());
                            // 自定义协议解码器
                            pipeline.addLast(new MessageDecoder());
                            // 自定义处理器
                            pipeline.addLast(new ClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(HOST, PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
