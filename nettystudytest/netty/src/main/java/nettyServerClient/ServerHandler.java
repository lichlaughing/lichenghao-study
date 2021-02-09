package nettyServerClient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 服务端处理器
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 读取消息
     *
     * @param ctx 上下文对象
     * @param msg 客户端发送的消息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 自定义普通任务提交到taskQueue中
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(5 * 1000);
                ByteBuf byteBuf = (ByteBuf) msg;
                System.out.println("客户端:"
                        + ctx.channel().remoteAddress() + "-说：" + byteBuf.toString(CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 自定义定时任务，提交到scheduledTaskQueue，5s之后执行
        ctx.channel().eventLoop().schedule(() -> {
            ByteBuf byteBuf = (ByteBuf) msg;
            System.out.println("客户端:"
                    + ctx.channel().remoteAddress() + "-说：" + byteBuf.toString(CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);

        // 调用其他的channel去发送消息
        ByteBuf byteBuf = (ByteBuf) msg;
        ByteBuf broadcastMsg =
                Unpooled.copiedBuffer(ctx.channel().remoteAddress() + "-说：" + byteBuf.toString(CharsetUtil.UTF_8), CharsetUtil.UTF_8);
        Set<SocketChannel> channelList = Server.channelList;
        channelList.stream().forEach(socketChannel -> {
            if (socketChannel != null && socketChannel != ctx.channel()) {
                socketChannel.writeAndFlush(Unpooled.copiedBuffer(broadcastMsg));
            }
        });
    }

    /**
     * 数据读取完毕，可以回复消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ByteBuf replyMsg =
                Unpooled.copiedBuffer("你好,客户端:" + ctx.channel().remoteAddress(), CharsetUtil.UTF_8);
        ctx.writeAndFlush(replyMsg);


    }

    /**
     * 发生异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
