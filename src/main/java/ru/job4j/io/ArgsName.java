package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Not found key %s", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String tmp = args[i];
            String[] tmpArr = tmp.split("=", 2);
            checkParams(tmpArr);
            tmpArr[0] = tmpArr[0].substring(1);
            values.put(tmpArr[0], tmpArr[1]);
        }

    }

    private void checkParams(String[] tmpArr) {
        if (tmpArr.length != 2 || tmpArr[1].isEmpty()) {
            throw new IllegalArgumentException(String.format("Param '%s' is invalid", tmpArr[0]));
        }
        if (!tmpArr[0].startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("The symbol '-' for the key '%s' is missing", tmpArr[0]))
                    ;
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Params are not defined");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8", "-Xmx1=1"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
