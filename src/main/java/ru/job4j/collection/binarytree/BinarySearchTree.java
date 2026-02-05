package ru.job4j.collection.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    private class Node {
        T value;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
        }
    }

    public boolean add(T value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }
        return add(root, value);
    }

    private boolean add(Node node, T value) {
        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node(value);
                return true;
            } else {
                return add(node.left, value);
            }
        } else if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new Node(value);
                return true;
            } else {
                return add(node.right, value);
            }
        }
        return false;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        inPostOrder(root, result);
        return result;
    }

    private void inPostOrder(Node node, List<T> list) {
        if (node != null) {
            inPostOrder(node.left, list);
            inPostOrder(node.right, list);
            list.add(node.value);
        }
    }

    public void clear() {
        clear(root);
        root = null;
    }

    private void clear(Node node) {
        if (node != null) {
            clear(node.left);
            clear(node.right);
            node.left = null;
            node.right = null;
            node.value = null;
        }
    }
}
