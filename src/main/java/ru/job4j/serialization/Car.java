package ru.job4j.serialization;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Serializable {
    private boolean old;
    private int age;
    private Additional additional;
    private String[] owners;

    public Car() {
    }

    public Car(boolean old, int age, Additional additional, String[] owners) {
        this.old = old;
        this.age = age;
        this.additional = additional;
        this.owners = owners;
    }

    public boolean isOld() {
        return old;
    }

    public void setOld(boolean old) {
        this.old = old;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Additional getAdditional() {
        return additional;
    }

    public void setAdditional(Additional additional) {
        this.additional = additional;
    }

    public String[] getOwners() {
        return owners;
    }

    public void setOwners(String[] owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{" +
                "old=" + old +
                ", age=" + age +
                ", additional=" + additional +
                ", owners=" + Arrays.toString(owners) +
                '}';
    }
}
