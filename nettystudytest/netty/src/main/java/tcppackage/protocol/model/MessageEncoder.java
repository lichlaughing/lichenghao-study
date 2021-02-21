package tcppackage.protocol.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 */
public class MessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf byteBuf) throws Exception {
        System.out.println("编码器执行~");
        byteBuf.writeInt(msg.getLen());
        byteBuf.writeBytes(msg.getContent());
    }
}
