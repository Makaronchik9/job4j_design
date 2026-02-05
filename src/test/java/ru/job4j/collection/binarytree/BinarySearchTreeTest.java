package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTreeTest {

    @Test
    void whenAddAndPostOrderThenCorrect() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        List<Integer> result = tree.inPostOrder();
        assertThat(result).containsExactly(2, 4, 3, 6, 8, 7, 5);
    }

    @Test
    void whenClearTreeThenEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.clear();
        List<Integer> result = tree.inPostOrder();
        assertThat(result).isEmpty();
    }
}
