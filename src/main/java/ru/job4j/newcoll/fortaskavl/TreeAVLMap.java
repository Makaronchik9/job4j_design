package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        boolean[] added = new boolean[1];
        root = insert(root, key, value, added);
        return added[0];
    }

    private Node insert(Node node, T key, V value, boolean[] added) {
        if (node == null) {
            added[0] = true;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value, added);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value, added);
        } else {
            node.value = value;
            added[0] = true;
        }
        updateHeight(node);
        return balance(node);
    }

    public boolean remove(T key) {
        boolean[] removed = new boolean[1];
        root = remove(root, key, removed);
        return removed[0];
    }

    private Node remove(Node node, T key, boolean[] removed) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key, removed);
        } else if (cmp > 0) {
            node.right = remove(node.right, key, removed);
        } else {
            removed[0] = true;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = findMin(node.right);
            node.key = min.key;
            node.value = min.value;
            node.right = remove(node.right, min.key, new boolean[1]);
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
        Set<T> set = new LinkedHashSet<>();
        inOrderKeys(root, set);
        return set;
    }

    private void inOrderKeys(Node node, Set<T> set) {
        if (node != null) {
            inOrderKeys(node.left, set);
            set.add(node.key);
            inOrderKeys(node.right, set);
        }
    }

    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        inOrderValues(root, list);
        return list;
    }

    private void inOrderValues(Node node, List<V> list) {
        if (node != null) {
            inOrderValues(node.left, list);
            list.add(node.value);
            inOrderValues(node.right, list);
        }
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        node.balanceFactor = height(node.right) - height(node.left);
    }

    private Node balance(Node node) {
        if (node.balanceFactor == 2) {
            if (node.right.balanceFactor < 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        } else if (node.balanceFactor == -2) {
            if (node.left.balanceFactor > 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node r = node.right;
        node.right = r.left;
        r.left = node;
        updateHeight(node);
        updateHeight(r);
        return r;
    }

    private Node rotateRight(Node node) {
        Node l = node.left;
        node.left = l.right;
        l.right = node;
        updateHeight(node);
        updateHeight(l);
        return l;
    }

    private class Node {
        private int balanceFactor;
        private int height;
        private T key;
        private V value;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }
}
