package ru.job4j.ood.ocp;

import java.util.Objects;

public class OrderExample1 {
    private String type;

    public OrderExample1(String getType) {
        this.type = getType;
    }

    public String getGetType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderExample1 orderExample1 = (OrderExample1) o;
        return Objects.equals(type, orderExample1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}