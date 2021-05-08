package cn.com.lichenghao.server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

import java.net.SocketAddress;
import java.util.Scanner;

/**
 * @author chenghao.li
 */
public class Server {

    private static SocketIOServer socketIOServer;

    public void initServer(String host, int port) {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);
        socketIOServer = new SocketIOServer(config);
    }

    public void start() {

        // 监听连接
        socketIOServer.addConnectListener(client -> {
            SocketAddress remoteAddress = client.getRemoteAddress();
            System.out.println(remoteAddress + ":连接成功!");
        });

        // 添加客户端断开连接事件
        socketIOServer.addDisconnectListener(client -> {
            String remoteAddress = client.getRemoteAddress().toString();
            System.out.println(remoteAddress + ":客户端已断开连接!");
        });

        // 监听自定义事件
        socketIOServer.addEventListener("org/client", String.class, (client, data, ackRequest) -> {
            // 客户端地址
            String remoteAddress = client.getRemoteAddress().toString();
            // 回复客户端
            client.sendEvent("org/client", remoteAddress + ",客户端你好," + data);
        });

        // 启动服务端
        socketIOServer.start();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.initServer("127.0.0.1", 9999);
        server.start();

        // 广播给客户端
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            socketIOServer.getBroadcastOperations().sendEvent("msg", msg);
        }
    }
}
