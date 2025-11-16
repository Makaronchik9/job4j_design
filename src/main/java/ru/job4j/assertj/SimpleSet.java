package ru.job4j.assertj;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSet {
    private String[] container = new String[2];
    private int size;

    public boolean add(String value) {
        if (size == container.length) {
            grow();
        }
        boolean result = !contains(value);
        if (result) {
            container[size++] = value;
        }
        return result;
    }

    private boolean contains(String value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(container[i], value)) {
                return true;
            }
        }
        return false;
    }

    private void grow() {
        int newLength = container.length * 2;
        container = Arrays.copyOf(container, newLength);
    }
}
