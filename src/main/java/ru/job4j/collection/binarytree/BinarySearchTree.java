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

    public boolean put(T value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }
        return put(root, value);
    }

    private boolean put(Node node, T value) {
        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node(value);
                return true;
            } else {
                return put(node.left, value);
            }
        } else if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new Node(value);
                return true;
            } else {
                return put(node.right, value);
            }
        }
        return false;
    }

    public boolean contains(T value) {
        Node node = root;
        while (node != null) {
            if (value.compareTo(node.value) < 0) {
                node = node.left;
            } else if (value.compareTo(node.value) > 0) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        inSymmetricalOrder(root, result);
        return result;
    }

    private void inSymmetricalOrder(Node node, List<T> result) {
        if (node != null) {
            inSymmetricalOrder(node.left, result);
            result.add(node.value);
            inSymmetricalOrder(node.right, result);
        }
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        inPreOrder(root, result);
        return result;
    }

    private void inPreOrder(Node node, List<T> result) {
        if (node != null) {
            result.add(node.value);
            inPreOrder(node.left, result);
            inPreOrder(node.right, result);
        }
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        inPostOrder(root, result);
        return result;
    }

    private void inPostOrder(Node node, List<T> result) {
        if (node != null) {
            inPostOrder(node.left, result);
            inPostOrder(node.right, result);
            result.add(node.value);
        }
    }

    public T minimum() {
        Node node = root;
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    public T maximum() {
        Node node = root;
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
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

    public boolean remove(T value) {
        Node parent = null;
        Node node = root;
        while (node != null && !node.value.equals(value)) {
            parent = node;
            if (value.compareTo(node.value) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            if (node == root) {
                root = null;
            } else if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (node.left != null && node.right == null) {
            if (node == root) {
                root = node.left;
            } else if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else if (node.left == null && node.right != null) {
            if (node == root) {
                root = node.right;
            } else if (parent.left == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else {
            Node successorParent = node;
            Node successor = node.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            node.value = successor.value;
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }
        return true;
    }
}
