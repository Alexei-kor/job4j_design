package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(15784)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream outputStream = socket.getOutputStream()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = bufferedReader.readLine();
                    System.out.println(str);
                    if (str.contains("msg=Bye")) {
                        server.close();
                    }
                    outputStream.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Server is not running", e);
        }
    }
}
