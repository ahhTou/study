package U19_网络编程.c3_TCP.c2_TCP通信练习.c3_服务端写入文本文件;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(9999);

        Socket s = ss.accept();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        s.getInputStream()
                )
        );
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(
                        "./src/U19_网络编程/c3_TCP/c2_TCP通信练习/c3_服务端写入文本文件/net.txt")
        );

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        bw.close();
        ss.close();


    }
}
