package websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * 服务端处理器
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 读取并回复消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame text) throws Exception {
        ctx.channel().writeAndFlush(
                new TextWebSocketFrame(LocalDateTime.now() + "-接收客户端消息：" + text.text()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 唯一ID
        System.out.println("客户端：" + ctx.channel().id().asLongText() + "-加入！");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端：" + ctx.channel().id().asLongText() + "-退出！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
