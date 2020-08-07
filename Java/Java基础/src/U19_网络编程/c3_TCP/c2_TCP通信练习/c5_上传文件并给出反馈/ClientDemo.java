package U19_网络编程.c3_TCP.c2_TCP通信练习.c5_上传文件并给出反馈;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("ahhTou", 9999);

        BufferedReader br = new BufferedReader(
                new FileReader("./src/U19_网络编程/c3_TCP/c2_TCP通信练习/c5_上传文件并给出反馈/Client.txt")
        );
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        s.getOutputStream()
                )
        );

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        System.out.println("等待接收反馈");
//        // 自定义结束标记
//        bw.write("886");
//        bw.newLine();
//        bw.flush();

        // 结束
        s.shutdownOutput();

        // 接收反馈
        BufferedReader brClient = new BufferedReader(
                new InputStreamReader(
                        s.getInputStream()
                )
        );
        String data = brClient.readLine();
        System.out.println("服务器的反馈：" + data);

        s.close();
        bw.close();

    }
}
