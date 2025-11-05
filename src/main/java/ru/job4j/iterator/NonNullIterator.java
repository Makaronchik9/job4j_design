package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private final Integer[] data;
    private int index = 0; // текущая позиция в массиве

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        // сдвигаем указатель на первый не-null элемент
        while (index < data.length && data[index] == null) {
            index++;
        }
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) { // если больше нет элементов
            throw new NoSuchElementException();
        }
        return data[index++]; // вернуть элемент и передвинуть указатель
    }
}
