package cn.com.lichenghao.client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author chenghao.li
 */
public class Client {

    public static void main(String[] args) {
        String url = "http://127.0.0.1:9999";
        try {
            // 连接参数
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            // 失败重试次数
            options.reconnectionAttempts = 10;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;
            // socket
            final Socket socket = IO.socket(url, options);
            // 监听连接相关
            socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
            socket.on(Socket.EVENT_CONNECT_TIMEOUT, objects -> System.out.println("client: " + "连接超时"));
            socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
            socket.on(Socket.EVENT_CONNECT, objects -> System.out.println("连接成功"));
            // 监听自定义/org/client事件
            socket.on("org/client", objects -> System.out.println(Arrays.asList(objects)));
            // 监听自定义/org/client事件
            socket.on("msg", objects -> System.out.println("广播消息：" + Arrays.asList(objects)));
            // 连接
            socket.connect();
            // 向服务端发送消息
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String msg = scanner.nextLine();
                socket.emit("org/client", msg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
