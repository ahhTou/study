package U19_网络编程.c3_TCP.c2_TCP通信练习.c5_上传文件并给出反馈;

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
                        "./src/U19_网络编程/c3_TCP/c2_TCP通信练习/c5_上传文件并给出反馈/Server.txt")
        );

        String line;
        while ((line = br.readLine()) != null) {
//            if ("886".equals(line)) {
//                break;
//            }
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        System.out.println("等待发送反馈");

        // 给出反馈
        BufferedWriter bwServer = new BufferedWriter(
                new OutputStreamWriter(
                        s.getOutputStream()
                )
        );
        bwServer.write("文件上传成功");
        bwServer.newLine();
        bwServer.flush();


        bw.close();
        ss.close();


    }
}
