package tcppackage.protocol.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 解码器
 */
public class MessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext cgx, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("解码器执行~");
        int i = byteBuf.readInt();
        byte[] bytes = new byte[i];
        byteBuf.readBytes(bytes);
        // 解析成自定义协议
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(i);
        messageProtocol.setContent(bytes);
        // 交给下个处理器
        list.add(messageProtocol);
    }
}
