package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source: sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<File> getSources(String source, String exclude) throws IOException {
        return Search.search(
                Paths.get(source),
                p -> !p.toFile().getName().endsWith(exclude)
        ).stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        checkParams(args);
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        checkDirectory(directory);
        Zip zip = new Zip();
        zip.packFiles(zip.getSources(directory, exclude), Paths.get(output).toFile());
    }

    private static void checkParams(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Must be 3 params: '-d', '-e', '-o'");
        }
    }

    private static void checkDirectory(String directory) {
        Path path = Paths.get(directory);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(
                    String.format("%s '%s' does't exist",
                            path.toFile().isDirectory() ? "Catalog" : "File",
                            directory)
            );
        }
    }
}
