package ru.job4j.newcoll.tree;

import ru.job4j.newcoll.tree.SimpleQueue;
import ru.job4j.newcoll.tree.Queue;
import ru.job4j.newcoll.tree.SimpleStack;

import java.util.*;

public class TreeUtils<T> {

    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            count++;
            for (Node<T> child : node.getChildren()) {
                queue.offer(child);
            }
        }
        return count;
    }

    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            result.add(node.getValue());
            for (Node<T> child : node.getChildren()) {
                queue.offer(child);
            }
        }
        return result;
    }

    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Optional<Node<T>> parentNode = findByKey(root, parent);
        Optional<Node<T>> childNode = findByKey(root, child);

        if (parentNode.isPresent() && childNode.isEmpty()) {
            parentNode.get().getChildren().add(new Node<>(child));
            return true;
        }
        return false;
    }

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (Objects.equals(node.getValue(), key)) {
                return Optional.of(node);
            }
            for (Node<T> child : node.getChildren()) {
                stack.push(child);
            }
        }
        return Optional.empty();
    }

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (Objects.equals(root.getValue(), key)) {
            return Optional.of(root);
        }

        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> parent = stack.pop();
            Iterator<Node<T>> it = parent.getChildren().iterator();
            while (it.hasNext()) {
                Node<T> child = it.next();
                if (Objects.equals(child.getValue(), key)) {
                    it.remove();
                    return Optional.of(child);
                }
                stack.push(child);
            }
        }
        return Optional.empty();
    }
}
