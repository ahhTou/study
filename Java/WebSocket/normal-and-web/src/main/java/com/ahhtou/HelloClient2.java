package com.ahhtou;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// 客户端2 负责发送
public class HelloClient2 {

    private final String host = "localhost";
    private final int port = 8080;

    private void send() throws InterruptedException {
        try {
            //创建Socket类对象
            try (Socket socket = new Socket(host, port)) {

                DataInputStream in = new DataInputStream(socket
                        .getInputStream());// 读取服务器端传过来信息的DataInputStream

                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());// 向服务器端发送信息的DataOutputStream

                Scanner scanner = new Scanner(System.in);

                while (true) {
                    String send = scanner.nextLine();//读取控制台输入的内容
                    System.out.println("发送 -> " + send);//输出键盘输出内容提示 ，也就是客户端向服务器端发送的消息
                    // 把从控制台得到的信息传送给MyServer
                    out.writeUTF(send);//将客户端的信息传递给服务器

                    String message = in.readUTF();// 读取来自服务器的信息
                    System.out.println("收到 -> " + message);//输出来自服务器返回的信息
                }

            }
            //关闭Socket监听
        } catch (Exception e) {
            System.out.println("连接异常, 1秒后将重试连接...");
            TimeUnit.SECONDS.sleep(1);
            this.send();
        }
    }

    private void onSend(DoHandler doHandler) throws IOException {
        doHandler.handler();
    }

/*    public static void main(String[] args) throws InterruptedException {
        System.out.println("端口号：8080 发送端 开始运行");
        HelloClient2 helloClient2 = new HelloClient2();
        helloClient2.send();
    }*/


}
