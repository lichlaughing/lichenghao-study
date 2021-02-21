package tcppackage.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import tcppackage.protocol.model.MessageProtocol;

import java.util.UUID;

public class ServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int sum;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol messageProtocol) throws Exception {
        // 读取客户端的消息
        String msg = new String(messageProtocol.getContent(), CharsetUtil.UTF_8);
        System.out.println("接收消息：" + msg);
        System.out.println("接收的消息数据的次数：" + (++this.sum));

        // 回应客户端消息
        MessageProtocol response = new MessageProtocol();
        String responseMsg = UUID.randomUUID().toString() + " ";
        response.setContent(responseMsg.getBytes(CharsetUtil.UTF_8));
        response.setLen(responseMsg.getBytes(CharsetUtil.UTF_8).length);
        ctx.writeAndFlush(response);
    }
}
