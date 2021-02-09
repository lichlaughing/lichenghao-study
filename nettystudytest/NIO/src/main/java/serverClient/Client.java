package serverClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 客户端通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 绑定服务
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);
        // 连接中
        if (!socketChannel.connect(socketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接中...");
            }
        }
        // 连接成功后
        ByteBuffer wrap = ByteBuffer.wrap("hello Server".getBytes());
        // 发送消息给服务
        socketChannel.write(wrap);
    }
}
