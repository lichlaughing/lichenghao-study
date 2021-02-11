package nettyChat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channels =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        channels.writeAndFlush("[客户端：" + socketAddress + "] 上线！");
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());
        channels.writeAndFlush("[客户端：" + ctx.channel().remoteAddress() + "] 下线！");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String getMsg = "[客户端：" + ctx.channel().remoteAddress() + "] 说：" + msg.toString();
        System.out.println(getMsg);
        channels.stream().forEach(channel -> {
            if (channel != ctx.channel()) {
                channel.writeAndFlush(getMsg);
            }
        });
    }
}
