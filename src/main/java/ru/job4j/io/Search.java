package ru.job4j.io;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        checkParams(args);
        String head = args[0];
        String ext = args[1];
        if (!ext.startsWith(".")) {
            ext = "." + ext;
        }
        final String extF = ext;
        search(Paths.get(head), p -> p.toFile().getName().endsWith(extF))
                .forEach(System.out::println);
    }

    public static void checkParams(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Must be 2 required params");
        }
        String head = args[0];
        Path path = Paths.get(head);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Сatalog '%s' does't exist", head));
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("'%s' is't catalog", head));
        }

        String ext = args[1];
        if (ext.isEmpty()) {
            throw new IllegalArgumentException("Empty extension");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}