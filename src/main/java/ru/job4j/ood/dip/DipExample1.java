package ru.job4j.ood.dip;

public class DipExample1 {

    // Нарушение DIP
    // класс верхнего уровня зависит от конкретной реализации MySQLDatabase,
    // а не от абстракции (интерфейса)
    private MySQLDatabase database;

    public DipExample1() {
        this.database = new MySQLDatabase();
    }

    public void createReport() {
    }

    public class MySQLDatabase {
    }
}
