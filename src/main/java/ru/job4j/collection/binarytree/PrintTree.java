package ru.job4j.collection.binarytree;

import java.util.*;

public class PrintTree {

    public static <T extends VisualNode> String getTreeDisplay(T root) {
        StringBuilder sb = new StringBuilder();
        display(sb, "", root, true);
        return sb.toString();
    }

    private static void display(StringBuilder sb, String prefix, VisualNode node, boolean isTail) {
        if (node != null) {
            sb.append(prefix)
                    .append(isTail ? "└── " : "├── ")
                    .append(node.getText())
                    .append("\n");

            VisualNode left = node.getLeft();
            VisualNode right = node.getRight();

            if (left != null || right != null) {
                if (right != null) {
                    display(sb, prefix + (isTail ? "    " : "│   "), right, left == null);
                }
                if (left != null) {
                    display(sb, prefix + (isTail ? "    " : "│   "), left, true);
                }
            }
        }
    }
}
