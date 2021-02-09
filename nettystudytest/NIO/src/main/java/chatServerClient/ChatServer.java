package chatServerClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ChatServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int port = 6666;

    public ChatServer() throws IOException {
        serverSocketChannel = serverSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        while (true) {
            if (selector.select(2000) > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 客户端连接
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress() + "-上线！");
                    }
                    // 读取客户端的信息
                    if (selectionKey.isReadable()) {
                        clientMsg(selectionKey);
                    }
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 读取客户端发送的消息
     *
     * @param selectionKey 客户端channel对应的selectionKey
     */
    private void clientMsg(SelectionKey selectionKey) {
        SocketChannel channel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = 0;
        try {
            channel = (SocketChannel) selectionKey.channel();
            read = channel.read(byteBuffer);
            if (read > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println(channel.getRemoteAddress() + "-说：" + msg);
                // 转发消息给其他客户端
                sendMsgToOthers(msg, channel);
            } else {
                System.out.println(channel.getRemoteAddress() + "-下线！");
                selectionKey.cancel();
                channel.close();
            }
        } catch (IOException e) {
            try {
                selectionKey.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 消费发送给其他客户端
     *
     * @param msg  消息内容
     * @param self 发送消息的channel，用来排除发送消息的客户端
     * @throws IOException
     */
    private void sendMsgToOthers(String msg, SocketChannel self) throws IOException {
        for (SelectionKey selectionKey : selector.keys()) {
            SelectableChannel channel = selectionKey.channel();
            if (channel instanceof SocketChannel && channel != self) {
                SocketChannel target = (SocketChannel) channel;
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                target.write(wrap);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // 启动服务端
        ChatServer chatServer = new ChatServer();
        // 监听消息
        chatServer.listen();
    }
}
