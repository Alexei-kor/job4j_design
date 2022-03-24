package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl==null) {
            throw new IllegalArgumentException(String.format("Not found key %s", key));
        }
        return rsl;
    }

    private void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String tmp = args[i];
            tmp = tmp.substring(1);
            String[] tmpArr = tmp.split("=", 2);
            if (tmpArr.length < 2 || tmpArr[1].isEmpty()) {
                throw new IllegalArgumentException(String.format("Error in the line '%s'", tmp));
            } else {
                values.put(tmpArr[0], tmpArr[1]);
            }
        }

    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8", "-Xmx1="});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
