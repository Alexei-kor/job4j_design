package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicate = new DuplicatesVisitor();
        Files.walkFileTree(Paths.get("./Data"), duplicate);
        duplicate.getDuplicateMap().entrySet().stream()
                .map(e -> String.format("%s, %s", e.getValue().getName(), e.getKey()))
                .sorted()
                .forEach(System.out::println);
    }
}
