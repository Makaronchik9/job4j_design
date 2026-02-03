package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array))
                .containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenEmptyArrayThenEmpty() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).isEmpty();
    }

    @Test
    void whenOneElementThenSame() {
        int[] array = {5};
        assertThat(Merge.mergesort(array)).containsExactly(5);
    }

    @Test
    void whenAlreadySorted() {
        int[] array = {1, 2, 3, 4, 5};
        assertThat(Merge.mergesort(array))
                .containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    void whenReverseSorted() {
        int[] array = {5, 4, 3, 2, 1};
        assertThat(Merge.mergesort(array))
                .containsExactly(1, 2, 3, 4, 5);
    }
}
