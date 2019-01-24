package com.hy.basics.socket.block;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientTest {

    public static void main(String[] args) throws IOException {
        String msg = "client data";
        Socket socket = new Socket("127.0.0.1", 8458);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(msg);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter.flush();
//        String line = bufferedReader.readLine();
//        System.out.println("SocketClientTest line = " + line);
        printWriter.close();
//        bufferedReader.close();
        socket.close();
    }
}
