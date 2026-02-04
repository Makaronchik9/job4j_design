package ru.job4j.newcoll.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeUtils<T> {

    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);
        int count = 0;

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            count++;

            for (Node<T> child : current.getChildren()) {
                queue.add(child);
            }
        }
        return count;
    }

    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Deque<Node<T>> queue = new ArrayDeque<>();
        List<T> result = new ArrayList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            result.add(current.getValue());

            for (Node<T> child : current.getChildren()) {
                queue.add(child);
            }
        }
        return result;
    }
}
