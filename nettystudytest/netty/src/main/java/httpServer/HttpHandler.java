package httpServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取消息
     *
     * @param ctx        上下文
     * @param httpObject 客户端的消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) httpObject;
            URI uri = new URI(request.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                return;
            }
            // 返回的消息内容
            ByteBuf responseContent =
                    Unpooled.copiedBuffer("This is Server ~", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, responseContent);
            // 设置头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, responseContent.readableBytes());
            // 返回消息
            ctx.writeAndFlush(response);
        }
    }
}
