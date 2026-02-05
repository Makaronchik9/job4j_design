package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<K extends Comparable<K>, V> {

    private Node root;
    private int size;

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        int height;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    public boolean put(K key, V value) {
        boolean[] added = {false};
        root = put(root, key, value, added);
        if (added[0]) {
            size++;
        }
        return true;
    }

    private Node<K, V> put(Node<K, V> node, K key, V value, boolean[] added) {
        if (node == null) {
            added[0] = true;
            return new Node<>(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value, added);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value, added);
        } else {
            node.value = value;
        }

        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node<K, V> node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    private int getBalance(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node<K, V> balance(Node<K, V> node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> y) {
        Node<K, V> x = y.left;
        Node<K, V> T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node<K, V> rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right;
        Node<K, V> T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        if (node != null) {
            return node.value;
        } else {
            return null;
        }
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        while (node != null) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return getNode(root, key) != null;
    }

    public int size() {
        return size;
    }

    public Set<K> keySet() {
        Set<K> keys = new LinkedHashSet<>();
        traverseKeys(root, keys);
        return keys;
    }

    private void traverseKeys(Node<K, V> node, Set<K> keys) {
        if (node != null) {
            traverseKeys(node.left, keys);
            keys.add(node.key);
            traverseKeys(node.right, keys);
        }
    }

    public List<V> values() {
        List<V> vals = new ArrayList<>();
        traverseValues(root, vals);
        return vals;
    }

    private void traverseValues(Node<K, V> node, List<V> vals) {
        if (node != null) {
            traverseValues(node.left, vals);
            vals.add(node.value);
            traverseValues(node.right, vals);
        }
    }

    public boolean remove(K key) {
        boolean[] removed = {false};
        root = remove(root, key, removed);
        if (removed[0]) {
            size--;
        }
        return removed[0];
    }

    private Node<K, V> remove(Node<K, V> node, K key, boolean[] removed) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key, removed);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key, removed);
        } else {
            removed[0] = true;
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<K, V> minLargerNode = getMin(node.right);
                node.key = minLargerNode.key;
                node.value = minLargerNode.value;
                node.right = remove(node.right, minLargerNode.key, new boolean[]{false});
            }
        }

        updateHeight(node);
        return balance(node);
    }

    private Node<K, V> getMin(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
