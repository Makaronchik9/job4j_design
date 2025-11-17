package ru.job4j.set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        // добавляем элемент 1
        assertThat(set.add(1)).isTrue();
        // проверяем, что он есть
        assertThat(set.contains(1)).isTrue();
        // повторное добавление должно вернуть false
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();

        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();

        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();

        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenIterateThenAllElementsReturned() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        // подсчитываем элементы через итератор
        int count = 0;
        for (Integer ignored : set) {
            count++;
        }
        assertThat(count).isEqualTo(3);

        // проверяем, что все элементы есть через contains
        assertThat(set.contains(10)).isTrue();
        assertThat(set.contains(20)).isTrue();
        assertThat(set.contains(30)).isTrue();
    }
}
