package ru.job4j.ood.lsp;

public class LspExample2 {

    public static class Database {

        public void connect() {
        }

        public void read() {
        }

        public void write() {
        }

        public void joinTables() {
        }
    }

    public static class MySqlDB extends Database {
    }

    public static class MongoDB extends Database {
    }
}