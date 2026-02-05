package ru.job4j.collection.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }

    public boolean put(T value) {
        if (root == null) {
            root = new Node<>(value);
            return true;
        }
        return put(root, value);
    }

    private boolean put(Node<T> node, T value) {
        int cmp = value.compareTo(node.value);
        if (cmp == 0) {
            return false;
        }
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node<>(value);
                return true;
            }
            return put(node.left, value);
        } else {
            if (node.right == null) {
                node.right = new Node<>(value);
                return true;
            }
            return put(node.right, value);
        }
    }

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        int cmp = value.compareTo(node.value);
        if (cmp == 0) {
            return true;
        }
        return cmp < 0
                ? contains(node.left, value)
                : contains(node.right, value);
    }

    public T maximum() {
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public T minimum() {
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
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
        if (node == null) {
            return;
        }
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
        if (node == null) {
            return;
        }
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.value);
    }

    public boolean remove(T value) {
        if (!contains(value)) {
            return false;
        }
        root = remove(root, value);
        return true;
    }

    private Node<T> remove(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        int cmp = value.compareTo(node.value);

        if (cmp < 0) {
            node.left = remove(node.left, value);
        } else if (cmp > 0) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            T min = findMin(node.right);
            node.value = min;
            node.right = remove(node.right, min);
        }
        return node;
    }

    private T findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }
}
