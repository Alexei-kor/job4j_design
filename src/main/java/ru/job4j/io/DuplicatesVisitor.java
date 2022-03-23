package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<Path, FileProperty> duplicateMap = new HashMap<>();
    private Map<FileProperty, Path> distinct = new HashMap<>();

    public Map<Path, FileProperty> getDuplicateMap() {
        return duplicateMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!attrs.isDirectory()) {
            FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());
            if (distinct.containsKey(fileProp)) {
                duplicateMap.put(distinct.get(fileProp), fileProp);
                duplicateMap.put(file.normalize(), fileProp);
            } else {
                distinct.put(fileProp, file.normalize());
            }
        }
        return super.visitFile(file, attrs);
    }
}
