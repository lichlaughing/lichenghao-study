package httpServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 加入netty提供的处理HTTP的编解码器
        socketChannel.pipeline().addLast("httpServerCodec", new HttpServerCodec());
        // 加入自己的处理器
        socketChannel.pipeline().addLast("serverHandler", new HttpHandler());
    }
}
