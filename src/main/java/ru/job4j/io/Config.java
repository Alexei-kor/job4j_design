package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while (bufferedReader.ready()) {
                String tmp = bufferedReader.readLine();
                if (tmp.isEmpty()
                        || tmp.startsWith("#")) {
                    continue;
                }
                List<String> list = Arrays.asList(tmp.split("="));
                if (list.isEmpty()
                        || list.size() < 2
                        || list.get(0).isEmpty()) {
                    throw new IllegalArgumentException("Неверный этот формат файла");
                }
                values.put(list.get(0), list.get(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        config.values.entrySet().stream()
                .map(e -> e.getKey())
                .forEach(System.out::println);
    }
}
