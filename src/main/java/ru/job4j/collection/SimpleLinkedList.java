package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Node<E> current = head;
            private final int expectedModCount = modCount;

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkModification();
                return current != null;
            }

            @Override
            public E next() {
                checkModification();
                if (current == null) {
                    throw new NoSuchElementException();
                }
                E value = current.item;
                current = current.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
