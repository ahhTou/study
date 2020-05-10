package U19_网络编程.c3_TCP.c2_TCP通信练习.c6_多线程实现文件上传;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(9999);


        while (true) {
            Socket s = ss.accept();
            new Thread(new ServerThread(s)).start();
        }

    }
}
