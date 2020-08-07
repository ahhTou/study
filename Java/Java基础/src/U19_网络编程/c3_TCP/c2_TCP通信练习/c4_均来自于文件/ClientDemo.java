package U19_网络编程.c3_TCP.c2_TCP通信练习.c4_均来自于文件;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("ahhTou", 9999);

        BufferedReader br = new BufferedReader(
                new FileReader("./src/U19_网络编程/c3_TCP/c2_TCP通信练习/c4_均来自于文件/Client.txt")
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

        s.close();
        bw.close();

    }
}
