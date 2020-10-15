package com.ahhtou;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

// 客户端1
public class HelloClient1 {

    private final int port = 8081;

    String newMessage = null;

    ServerSocket server;
    Socket socket =null;
    DataInputStream in = null;
    DataOutputStream out = null;

    public void service() throws Exception {
        try {
            // 初始化
            init();

            // 接收到 message 的时候 回调函数（误）
            onMessage(msg -> {
                System.out.println("收到了：" + msg);
                out.writeUTF("对方收到信息");
            });

        }catch (Exception e){

            System.out.println("出现异常，重试连接...");
            TimeUnit.SECONDS.sleep(1);
            this.destroy();
            this.service();

        }


    }

    private void init() throws Exception {
        server = new ServerSocket(port);
        socket = server.accept();
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        System.out.println("初始化成功...");
    }
    private void destroy() throws Exception {
        out.close();
        in.close();
        socket.close();
        server.close();
        System.out.println("关闭成功...");
    }

    private void onMessage(DoArgs doArgs) throws Exception {
        this.newMessage = in.readUTF();
        if (this.newMessage.equals("/close")) return;
        doArgs.handler(this.newMessage);
        onMessage(doArgs);
    }


/*
    public static void main(String[] args) throws Exception {
        System.out.println("端口号：8080 接收端开始 开始运行");
        HelloClient1 helloClient1 = new HelloClient1();
        helloClient1.service();
    }
*/


}
