package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
class QuickListTest {
    @Test
    void whenEmptyListThenOk() {
        List<Integer> list = new ArrayList<>();
        QuickList.quickSort(list, Integer::compareTo);
        assertThat(list).isEmpty();
    }

    @Test
    void whenOneElementThenOk() {
        List<Integer> list = List.of(5);
        QuickList.quickSort(new ArrayList<>(list), Integer::compareTo);
    }
}