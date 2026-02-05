package ru.job4j.collection.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;

    public boolean put(T value) {
        if (root == null) {
            root = new Node<>(value);
            return true;
        }
        return add(root, value);
    }

    private boolean add(Node<T> node, T value) {
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node<>(value);
                return true;
            }
            return add(node.left, value);
        } else if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node<>(value);
                return true;
            }
            return add(node.right, value);
        } else {
            return false;
        }
    }

    public boolean contains(T value) {
        return find(root, value);
    }

    private boolean find(Node<T> node, T value) {
        if (node == null) return false;
        int cmp = value.compareTo(node.value);
        if (cmp == 0) return true;
        return cmp < 0 ? find(node.left, value) : find(node.right, value);
    }

    public T minimum() {
        if (root == null) return null;
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public T maximum() {
        if (root == null) return null;
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, List<T> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.value);
        inOrder(node.right, list);
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node<T> node, List<T> list) {
        if (node == null) return;
        list.add(node.value);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(Node<T> node, List<T> list) {
        if (node == null) return;
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.value);
    }

    public boolean remove(T value) {
        if (!contains(value)) return false;
        root = remove(root, value);
        return true;
    }

    private Node<T> remove(Node<T> node, T value) {
        if (node == null) return null;
        int cmp = value.compareTo(node.value);
        if (cmp < 0) node.left = remove(node.left, value);
        else if (cmp > 0) node.right = remove(node.right, value);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node<T> min = node.right;
            while (min.left != null) min = min.left;
            node.value = min.value;
            node.right = remove(node.right, min.value);
        }
        return node;
    }

    public void clear() {
        clear(root);
        root = null;
    }

    private void clear(Node<T> node) {
        if (node == null) return;
        clear(node.left);
        clear(node.right);
        node.left = null;
        node.right = null;
        node.value = null;
    }
}
