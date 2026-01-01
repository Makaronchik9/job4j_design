package ru.job4j.ood.srp;

import java.util.List;

public class SrpExample2 {
    private List<SrpExample2> orders;

    public void addOrder(SrpExample2 order) {
        orders.add(order);
    }

    public void removeOrder(SrpExample2 order) {
        orders.remove(order);
    }

    public void printOrder(SrpExample2 order) {
        System.out.println("Order Summary: ");
    }
}