package ru.job4j.io;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {

        String head = args[0];
        if (head.isEmpty()) {
            throw new IllegalArgumentException("Empty head catalog");
        }

        String ext = args[1];
        if (ext.isEmpty()) {
            throw new IllegalArgumentException("Empty extension");
        }
        if (!ext.startsWith(".")) {
            ext = "." + ext;
        }
        final String extF = ext;

        Path start = Paths.get(head);
        search(start, p -> p.toFile().getName().endsWith(extF)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
