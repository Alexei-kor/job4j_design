package ru.job4j.jdbc;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Config;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        checkParams(args);
        ArgsName argsName = ArgsName.of(args);
        Config config = new Config(argsName.get("path"));
        config.load();
        Class.forName(config.value("driver"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData data = connection.getMetaData();
            System.out.println(data.getUserName());
            System.out.println(data.getURL());
        }
    }
    public static void checkParams(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Must be 1 parameter '-path'");
        }
    }

}
