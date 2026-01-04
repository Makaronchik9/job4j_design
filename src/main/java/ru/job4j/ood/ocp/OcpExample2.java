package ru.job4j.ood.ocp;

import java.util.List;

public class OcpExample2 {
    private static class Triangle {
        public String color() {
            return "purple";
        }
    }

    public static void main(String[] args) {
        List<Triangle> cars = List.of(new Triangle(), new Triangle());
        cars.forEach(Triangle::color);
    }
}