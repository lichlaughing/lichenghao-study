package nettyChat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class ChatClient {
    private String host;
    private int port;

    public ChatClient() {
    }

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void start() {

        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("stringEncoder", new StringEncoder());
                            pipeline.addLast("stringDecoder", new StringDecoder());
                            pipeline.addLast("chatClientHandler", new ChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            // 发送消息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String toMsg = scanner.nextLine();
                channel.writeAndFlush(toMsg + "\r");
            }
            // 监听关闭通道
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatClient("127.0.0.1", 6668).start();
    }
}
