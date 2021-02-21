package tcppackage.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int sum;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        // 读取客户端的消息
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String msg = new String(bytes, CharsetUtil.UTF_8);
        System.out.println("接收消息：" + msg);
        System.out.println("接收的消息数据的次数：" + (++this.sum));

        // 回应客户端消息
        ByteBuf response =
                Unpooled.copiedBuffer(UUID.randomUUID().toString() + " ", CharsetUtil.UTF_8);
        ctx.writeAndFlush(response);
    }
}
