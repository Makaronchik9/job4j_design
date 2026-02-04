package ru.job4j.collection.binarytree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        if (Objects.isNull(root)) {
            root = new Node(key);
            return true;
        }
        return put(root, key);
    }

    private boolean put(Node node, T key) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(key);
                return true;
            } else return put(node.left, key);
        } else if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(key);
                return true;
            } else return put(node.right, key);
        }
        return false;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        return cmp < 0 ? find(node.left, key) : find(node.right, key);
    }

    public boolean remove(T key) {
        if (root == null || key == null) return false;
        boolean[] removed = new boolean[]{false};
        root = removeNode(root, key, removed);
        return removed[0];
    }

    private Node removeNode(Node node, T key, boolean[] removed) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeNode(node.left, key, removed);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, key, removed);
        } else {
            removed[0] = true;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;


            Node successor = minimum(node.right);
            node.key = successor.key;
            node.right = removeNode(node.right, successor.key, new boolean[]{false});
        }
        return node;
    }

    public T minimum() {
        return root != null ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public T maximum() {
        return root != null ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        return inSymmetricalOrder(root, result);
    }

    private List<T> inSymmetricalOrder(Node node, List<T> list) {
        if (node != null) {
            inSymmetricalOrder(node.left, list);
            list.add(node.key);
            inSymmetricalOrder(node.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        return inPreOrder(root, result);
    }

    private List<T> inPreOrder(Node node, List<T> list) {
        if (node != null) {
            list.add(node.key);
            inPreOrder(node.left, list);
            inPreOrder(node.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        return inPostOrder(root, result);
    }

    private List<T> inPostOrder(Node node, List<T> list) {
        if (node != null) {
            inPostOrder(node.left, list);
            inPostOrder(node.right, list);
            list.add(node.key);
        }
        return list;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}
