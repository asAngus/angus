package com.p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 外网端服务，穿透中继
 *
 * @author ln
 */
public class Server {

    public static List<ServerThread> connections = new ArrayList<ServerThread>();

    public static void main(String[] args) {
        try {
            // 1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
            // 记录客户端的数量
            int count = 0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            // 循环监听等待客户端的连接
            while (true) {
                // 调用accept()方法开始监听，等待客户端的连接
                socket = serverSocket.accept();
                // 创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                // 启动线程
                serverThread.start();

                connections.add(serverThread);

                count++;// 统计客户端的数量
                System.out.println("客户端的数量：" + count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
