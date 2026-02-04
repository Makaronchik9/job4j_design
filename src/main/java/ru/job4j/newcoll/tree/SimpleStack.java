package ru.job4j.newcoll.tree;

import java.util.LinkedList;

public class SimpleStack<T> {

    private final LinkedList<T> stack = new LinkedList<>();

    public void push(T value) {
        stack.addLast(value);
    }

    public T pop() {
        return stack.removeLast();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}