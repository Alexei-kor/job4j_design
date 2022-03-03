package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ResultFile {
    public static void main(String[] args) {
        write();
        read();
    }

    public static void write() {
        try (FileOutputStream out = new FileOutputStream("text.txt")) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    tmp.append(i * j);
                    tmp.append(i * j < 10 ? "  " : " ");
                }
                tmp.append(System.lineSeparator());
            }
            out.write(tmp.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try (BufferedReader buffRead = new BufferedReader(new FileReader("input.txt"))) {
            buffRead.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
