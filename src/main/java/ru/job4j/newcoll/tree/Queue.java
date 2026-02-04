package ru.job4j.newcoll.tree;

public interface Queue<E> {

    void offer(E value);

    E poll();

    boolean isEmpty();
}