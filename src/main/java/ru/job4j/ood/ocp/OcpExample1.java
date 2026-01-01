package ru.job4j.ood.ocp;

public class OcpExample1 {
    public double discount(OrderExample1 orderExample1) {
        double dis = 0;
        String standart = "standart";
        String premium = "premium";
        if (standart.equals(orderExample1.getGetType())) {
            dis = 0.2;
        } else if (premium.equals(orderExample1.getGetType())) {
            dis = 0.3;
        }
        return dis;
    }
}