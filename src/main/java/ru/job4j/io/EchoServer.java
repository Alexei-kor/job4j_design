package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(15784)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream outputStream = socket.getOutputStream()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = bufferedReader.readLine();
                    System.out.println(str);
                    if (str.contains("msg=Hello")) {
                        outputStream.write("Hello".getBytes());
                    } else if (str.contains("msg=Exit")) {
                        server.close();
                    } else {
                        outputStream.write("What".getBytes());
                    }
                    outputStream.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
