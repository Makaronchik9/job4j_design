package ru.job4j.jdbc;

import java.io.InputStream;
import java.util.Properties;

public class TableEditorDemo {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        try (InputStream in = TableEditorDemo.class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
        }

        try (TableEditor editor = new TableEditor(properties)) {

            editor.dropTable("demo");
            editor.createTable("demo");
            System.out.println(editor.getTableScheme("demo"));

            editor.addColumn("demo", "id", "serial primary key");
            System.out.println(editor.getTableScheme("demo"));

            editor.addColumn("demo", "name", "text");
            System.out.println(editor.getTableScheme("demo"));

            editor.renameColumn("demo", "name", "username");
            System.out.println(editor.getTableScheme("demo"));

            editor.dropColumn("demo", "username");
            System.out.println(editor.getTableScheme("demo"));
        }
    }
}
