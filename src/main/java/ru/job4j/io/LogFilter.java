package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader("log.txt"))) {
            list = buffRead.lines()
                    .filter(e -> e.matches(".*\\s404\\s.*"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(List<String> list, String name) {
        try (PrintWriter printWriter = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(name)))) {
            list.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        logFilter.save(log, "404.txt");
    }

}
