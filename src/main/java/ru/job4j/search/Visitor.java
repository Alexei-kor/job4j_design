package ru.job4j.search;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Visitor extends SimpleFileVisitor<Path> {

    String type;
    String name;
    List<Path> list = new ArrayList<>();

    public Visitor(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (("name".equals(type) && findByName(file, name))
                || ("regex".equals(type) && findByRegex(file, name))
                || ("mask".equals(type) && findByMask(file, name))) {
            list.add(file.normalize());
        }
        return super.visitFile(file, attrs);
    }

    public static boolean findByName(Path file, String name) {
        boolean rsl = false;
        String nameWithOutExt = file.toFile().getName().substring(
                0,
                file.toFile().getName().lastIndexOf(".")
        );
        if (!nameWithOutExt.isEmpty()
                && nameWithOutExt.equals(name)) {
            rsl = true;
        }
        return rsl;
    }

    public static boolean findByRegex(Path file, String name) {
        Pattern pattern = Pattern.compile(name);
        return pattern.matcher(file.toFile().getName()).find();
    }

    private static boolean findByMask(Path file, String mask) {
        FileFilter fileFilter = new WildcardFileFilter(mask);
        return fileFilter.accept(file.toFile());
    }
}
