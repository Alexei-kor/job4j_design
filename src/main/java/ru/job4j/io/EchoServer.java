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
            boolean stop = false;
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream outputStream = socket.getOutputStream()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = bufferedReader.readLine(); str != null && !str.isEmpty(); str = bufferedReader.readLine()) {
                        System.out.println(str);
                        if (str.contains("msg=Bye")) {
                            stop = true;
                        }
                    }
                    outputStream.flush();
                    if (stop) {
                        server.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
