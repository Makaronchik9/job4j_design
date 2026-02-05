package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        if (key == null) {
            return false;
        }
        root = insert(root, key, value);
        return true;
    }

    private Node insert(Node node, T key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }
        updateHeight(node);
        return balance(node);
    }

    public boolean remove(T key) {
        if (key == null || root == null || !contains(root, key)) {
            return false;
        }
        root = remove(root, key);
        return true;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = minimumNode(node.right);
            node.key = min.key;
            node.value = min.value;
            node.right = remove(node.right, min.key);
        }
        updateHeight(node);
        return balance(node);
    }

    public V get(T key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }

    public Set<T> keySet() {
        Set<T> keys = new LinkedHashSet<>();
        inOrderKeys(root, keys);
        return keys;
    }

    private void inOrderKeys(Node node, Set<T> keys) {
        if (node != null) {
            inOrderKeys(node.left, keys);
            keys.add(node.key);
            inOrderKeys(node.right, keys);
        }
    }

    public Collection<V> values() {
        List<V> vals = new ArrayList<>();
        inOrderValues(root, vals);
        return vals;
    }

    private void inOrderValues(Node node, List<V> vals) {
        if (node != null) {
            inOrderValues(node.left, vals);
            vals.add(node.value);
            inOrderValues(node.right, vals);
        }
    }

    private boolean contains(Node node, T key) {
        if (node == null) return false;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return contains(node.left, key);
        else if (cmp > 0) return contains(node.right, key);
        else return true;
    }

    private void updateHeight(Node node) {
        int lh = node.left == null ? -1 : node.left.height;
        int rh = node.right == null ? -1 : node.right.height;
        node.height = 1 + Math.max(lh, rh);
        node.balanceFactor = rh - lh;
    }

    private Node balance(Node node) {
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor > 0) {
                node.left = leftRotation(node.left);
            }
            return rightRotation(node);
        }
        if (node.balanceFactor > 1) {
            if (node.right.balanceFactor < 0) {
                node.right = rightRotation(node.right);
            }
            return leftRotation(node);
        }
        return node;
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node minimumNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private class Node {
        T key;
        V value;
        int height;
        int balanceFactor;
        Node left;
        Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
