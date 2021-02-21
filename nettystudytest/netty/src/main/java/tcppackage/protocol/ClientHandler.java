package tcppackage.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import tcppackage.protocol.model.MessageProtocol;

public class ClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 连续发送5条消息
        for (int i = 0; i < 5; i++) {
            String msg = "老子吃火锅~ " + i;
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(msg.getBytes(CharsetUtil.UTF_8).length);
            messageProtocol.setContent(msg.getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol messageProtocol) throws Exception {
        // 读取服务端返回的消息
        byte[] content = messageProtocol.getContent();
        String msg = new String(content, CharsetUtil.UTF_8);
        System.out.println("接收消息：" + msg);
    }
}
