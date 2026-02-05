package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void whenAddAndTraverseTreeThenCorrect() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(i);
        }
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 2, 3, 4, 5, 6, 7);
        assertThat(tree.inPreOrder()).containsExactly(4, 2, 1, 3, 6, 5, 7);
        assertThat(tree.inPostOrder()).containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenRemoveNodesThenCorrect() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(i);
        }
        tree.remove(6);
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 2, 3, 4, 5, 7);
        tree.remove(2);
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 3, 4, 5, 7);
        tree.remove(10); // несуществующий
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 3, 4, 5, 7);
    }

    @Test
    void whenClearTreeThenEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(i);
        }
        tree.clear();
        assertThat(tree.inSymmetricalOrder()).isEmpty();
        assertThat(tree.contains(4)).isFalse();
        assertThat(tree.contains(1)).isFalse();
    }

    @Test
    void whenContainsWorks() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        assertThat(tree.contains("first")).isTrue();
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("third")).isFalse();
    }

    @Test
    void whenMinMaxWorks() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(i);
        }
        assertThat(tree.minimum()).isEqualTo(1);
        assertThat(tree.maximum()).isEqualTo(7);
    }
}
