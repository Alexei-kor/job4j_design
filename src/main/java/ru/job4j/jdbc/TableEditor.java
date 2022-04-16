package ru.job4j.jdbc;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("create table if not exists %s();", tableName);
        execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table %s;", tableName);
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("alter table %s add if not exists %s %s;", tableName, columnName, type);
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column %s;", tableName, columnName);
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("alter table %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        execute(sql);
    }

    private Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    private void execute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("paramsTableEditor.properties")) {
            properties.load(in);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                String tableName = "city";
                tableEditor.createTable(tableName);
                tableEditor.console(tableName);
                tableEditor.addColumn(tableName, "id", "serial primary key");
                tableEditor.console(tableName);
                tableEditor.addColumn(tableName, "name", "varchar(255)");
                tableEditor.console(tableName);
                tableEditor.addColumn(tableName, "dateBegin", "date");
                tableEditor.console(tableName);
                tableEditor.renameColumn(tableName, "dateBegin", "date");
                tableEditor.console(tableName);
                tableEditor.dropColumn(tableName, "date");
                tableEditor.console(tableName);
                tableEditor.dropTable(tableName);
            }

        }
    }

    public void console(String tableName) throws Exception {
        System.out.println(TableEditor.getTableScheme(connection, tableName));
    }
}
