package heartBeat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;

/**
 * 服务端处理器
 */
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

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            switch (stateEvent.state()) {
                case READER_IDLE:
                    System.out.println("读空闲！");
                    break;
                case WRITER_IDLE:
                    System.out.println("写空闲");
                    break;
                case ALL_IDLE:
                    System.out.println("读写空闲！");
                    break;
                default:
                    System.out.println("其他空闲！");
            }
        }
    }
}
