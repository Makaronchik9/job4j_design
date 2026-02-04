package ru.job4j.newcoll.tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Простая очередь на базе LinkedList
 */
public class SimpleQueue<E> implements Queue<E> {

    private LinkedList<E> list = new LinkedList<>();

    @Override
    public void offer(E value) {
        list.addLast(value);
    }

    @Override
    public E poll() {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}