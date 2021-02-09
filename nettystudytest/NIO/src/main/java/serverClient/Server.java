package serverClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 服务端
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 得到一个ServerSocketChannel服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置通道异步
        serverSocketChannel.configureBlocking(false);
        // 监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 注册到Selector上，注册类型有四种：OP_ACCEPT:有新的网络可连接；OP_CONNECT:已经建立连接；OP_READ:读操作；OP_WRITE:写操作
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 监听通道上是否有事件发生
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (selectionKeys != null && !selectionKeys.isEmpty()) {
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        // 连接请求,为客户端生成一个socketChannel
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 设置非阻塞
                        socketChannel.configureBlocking(false);
                        // 客户端注册到Selector上
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                        channel.read(byteBuffer);
                        System.out.println("Client:" + new String(byteBuffer.array()));
                    }
                    // 处理完毕的清除掉
                    iterator.remove();
                }
            }
        }
    }
}
