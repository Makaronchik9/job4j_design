package ru.job4j.collection.binarytree;

public class TreePrinter {

    public static String getTreeDisplay(VisualNode root) {
        StringBuilder sb = new StringBuilder();
        print(root, "", true, sb);
        return sb.toString();
    }

    private static void print(VisualNode node, String prefix, boolean isTail, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(prefix)
                .append(isTail ? "└── " : "├── ")
                .append(node.getText())
                .append("\n");

        print(node.getLeft(), prefix + (isTail ? "    " : "│   "), false, sb);
        print(node.getRight(), prefix + (isTail ? "    " : "│   "), true, sb);
    }
}
