package chatServerClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ChatClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6666;
    private SocketChannel socketChannel;
    private Selector selector;
    private String currentIp;

    public ChatClient() throws IOException {
        selector = selector.open();
        socketChannel = socketChannel.open(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        currentIp = socketChannel.getLocalAddress().toString();
    }

    /**
     * 发送消息
     *
     * @param msg 消息内容
     */
    private void chat(String msg) {
        String toMsg = currentIp + "-say:" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(toMsg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取其他人的消息
     *
     * @throws IOException
     */
    private void getMsg() throws IOException {
        int select = selector.select();
        if (select > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey se = iterator.next();
                if (se.isReadable()) {
                    SocketChannel channel = (SocketChannel) se.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    System.out.println(new String(buffer.array()));
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 启动客户端
        ChatClient chatClient = new ChatClient();
        // 读取他人的消息
        new Thread(() -> {
            while (true) {
                try {
                    chatClient.getMsg();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // 发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            chatClient.chat(msg);
        }
    }
}
