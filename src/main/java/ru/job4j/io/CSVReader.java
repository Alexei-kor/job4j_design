package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CSVReader {
    public static void main(String[] args) throws IOException {
        checkParams(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    private static void checkParams(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Must be 4 params: '-path', '-delimiter', '-out', '-filter'.");
        }
    }

    public static void handle(ArgsName argsName) throws IOException {
        String pathCSV = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        Scanner scanner = new Scanner(new FileInputStream(pathCSV), Charset.forName("UTF-8"));
        scanner.useDelimiter(delimiter);
        List<String> filds = Arrays.asList(filter.split(","));
        List<Integer> indexexF = new ArrayList<>(filds.size());
        StringBuilder sb = new StringBuilder();
        /**
         * вначале обрабатываем заголовки, ищем по ним индексы ячеек, выводим заголовки
         */
        if (scanner.hasNextLine()) {
            List<String> head = Arrays.asList(scanner.nextLine().split(";"));
            indexexF = filds.stream().map(e -> head.indexOf(e)).filter(e -> e != -1).collect(Collectors.toList());
            indexexF.stream().map(e -> head.get(e) + ";").forEach(sb::append);
            if (sb.isEmpty()) {
                throw new IllegalArgumentException(String.format("Not find filds: '%s'", filter));
            }
            sb = sb.deleteCharAt(sb.lastIndexOf(";"));
        }
        /**
         * обрабатываем остальные строки, используя найденные ранее индексы
         */
        while (scanner.hasNextLine()) {
            sb.append(System.lineSeparator());
            String[] tmp = scanner.nextLine().split(";");
            indexexF.stream().map(e -> tmp[e] + ";").forEach(sb::append);
            sb = sb.deleteCharAt(sb.lastIndexOf(";"));
        }
        /**
         * вывод результата
         */
        if (!"stdout".equals(out)) {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(out))) {
                printWriter.println(sb.toString());
            }
        } else {
            System.out.println(sb.toString());
        }
    }
}
