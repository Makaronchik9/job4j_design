package ru.job4j.ood.ocp;

public class OcpExample3 {
    private String name;
    private int damage;

    public OcpExample3(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public void attack() {
        System.out.println("damage with sword is " + this.damage);
    }
}