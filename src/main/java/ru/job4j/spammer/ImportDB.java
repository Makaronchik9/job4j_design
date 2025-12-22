package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(line -> {
                String[] parts = line.split(";");
                if (parts.length != 2
                        || parts[0].isBlank()
                        || parts[1].isBlank()) {
                    throw new IllegalArgumentException("Invalid line: " + line);
                }
                users.add(new User(parts[0], parts[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        );
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users(name, email) VALUES (?, ?)")
        ) {
            for (User user : users) {
                statement.setString(1, user.name);
                statement.setString(2, user.email);
                statement.execute();
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB db = new ImportDB(config, "./dump.txt");
        db.save(db.load());
    }
}
