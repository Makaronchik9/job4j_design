package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(line -> !line.startsWith("#"))
                    .filter(line -> line.contains("="))
                    .forEach(line -> {
                        String[] split = line.split("=", 2);
                        values.put(split[0], split[1]);
                    });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}
