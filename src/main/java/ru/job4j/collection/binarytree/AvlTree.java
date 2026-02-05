package ru.job4j.collection.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvlTree<T extends Comparable<T>> {

    public Node<T> root;

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        int cmp = value.compareTo(node.key);
        if (cmp == 0) {
            return true;
        }
        return cmp < 0 ? contains(node.left, value) : contains(node.right, value);
    }

    public boolean insert(T value) {
        if (value == null || contains(value)) {
            return false;
        }
        root = insert(root, value);
        return true;
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }
        if (value.compareTo(node.key) < 0) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        updateHeight(node);
        return balance(node);
    }

    public boolean remove(T value) {
        if (value == null || root == null || !contains(value)) {
            return false;
        }
        root = remove(root, value);
        return true;
    }

    private Node<T> remove(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.key);
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
            Node<T> min = minimumNode(node.right);
            node.key = min.key;
            node.right = remove(node.right, min.key);
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node<T> node) {
        int lh = node.left == null ? -1 : node.left.height;
        int rh = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(lh, rh);
        node.balance = rh - lh;
    }

    private Node<T> balance(Node<T> node) {
        if (node.balance < -1) {
            if (node.left.balance > 0) {
                node.left = leftRotation(node.left);
            }
            return rightRotation(node);
        }
        if (node.balance > 1) {
            if (node.right.balance < 0) {
                node.right = rightRotation(node.right);
            }
            return leftRotation(node);
        }
        return node;
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public T minimum() {
        if (root == null) {
            return null;
        }
        return minimumNode(root).key;
    }

    private Node<T> minimumNode(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public T maximum() {
        if (root == null) {
            return null;
        }
        Node<T> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, List<T> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.key);
            inOrder(node.right, list);
        }
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node<T> node, List<T> list) {
        if (node != null) {
            list.add(node.key);
            preOrder(node.left, list);
            preOrder(node.right, list);
        }
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(Node<T> node, List<T> list) {
        if (node != null) {
            postOrder(node.left, list);
            postOrder(node.right, list);
            list.add(node.key);
        }
    }

    static class Node<T> {
        T key;
        int height;
        int balance;
        Node<T> left;
        Node<T> right;

        Node(T key) {
            this.key = key;
        }
    }
}
