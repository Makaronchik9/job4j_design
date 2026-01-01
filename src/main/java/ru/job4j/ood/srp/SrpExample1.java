package ru.job4j.ood.srp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SrpExample1 {
    private String name;
    private String surname;

    public SrpExample1(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void saveToFile(String filename) {
        try {
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Name: " + name + ", Surname: " + surname);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}