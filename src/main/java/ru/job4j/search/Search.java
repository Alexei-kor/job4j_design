package ru.job4j.search;

import ru.job4j.io.ArgsName;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Search {
    public static void main(String[] args) throws IOException {
        checkParams(args);
        ArgsName argsName = ArgsName.of(args);
        String dir = argsName.get("d");
        String name = argsName.get("n");
        String type = argsName.get("t");
        String out = argsName.get("o");

        if (!("mask".equals(type) || "name".equals(type) || "regex".equals(type))) {
            throw new IllegalArgumentException("Param '-t' must be value 'name' or 'mask' or 'regex'");
        }
        try (PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)))) {
            Visitor visitor = new Visitor(type, name);
            Files.walkFileTree(Paths.get(dir), visitor);
            visitor.getList().forEach(pw::println);
        }
    }

    private static void checkParams(String[] args) {
        if (args.length != 4) {
            String err = String.join(System.lineSeparator(),
                    "Must be 4 params: ",
                    "-d (catalog for search)",
                    "-n (name file or mask or regexp)",
                    "-t (type of search)",
                    "-o (output)");
            throw new IllegalArgumentException(err);
        }
    }
}
