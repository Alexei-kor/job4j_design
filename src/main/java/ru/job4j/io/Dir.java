package ru.job4j.io;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

public class Dir {
    public static void main(String[] args) {
        File file = new File("./Data");
        if (!file.exists()) {
            new IllegalArgumentException(String.format("No exist %s", file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            new IllegalArgumentException(String.format(String.format("It'n directory %s", file.getAbsolutePath())));
        }
        System.out.println(String.format("size %s", file.getTotalSpace()));
        Arrays.stream(file.listFiles())
                .filter(e -> !e.isDirectory())
                .map(e -> String.format("Name my: '%s', size: %s", e.getName(), e.length()))
                .forEach(System.out::println);
    }
}
