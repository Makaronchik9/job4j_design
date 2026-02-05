package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class AvlTreeTest {

    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenInsertThenBalanced() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        assertThat(tree.root.key).isEqualTo(2);
    }

    @Test
    void whenRemoveThenBalanced() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i <= 7; i++) {
            tree.insert(i);
        }
        tree.remove(4);
        assertThat(tree.inSymmetricalOrder())
                .containsExactly(1, 2, 3, 5, 6, 7);
    }

    @Test
    void whenMinimumThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(2);
        tree.insert(8);
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenMaximumThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(5);
        tree.insert(2);
        tree.insert(8);
        assertThat(tree.maximum()).isEqualTo(8);
    }
}