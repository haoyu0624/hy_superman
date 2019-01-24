package com.hy.basics.socket.block;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServiceTest {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8458);
        Socket accept = server.accept();
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        String line = bufferedReader.readLine();
        System.out.println("SocketServiceTest line = " + line);
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
        printWriter.println(line);
        printWriter.flush();
        printWriter.close();
        bufferedReader.close();
        accept.close();
        server.close();
    }
}
