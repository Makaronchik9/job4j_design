package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SimpleLinkedListTest {

    private SimpleLinked<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
    }

    /* ---------- БАЗОВЫЕ ТЕСТЫ ИЗ ЗАДАНИЯ ---------- */

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddIterHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOne() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHashNextFalse() {
        SimpleLinked<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenAddIterMultiHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOneNextTwo() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();

        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
    }

    /* ---------- ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ ---------- */

    // ✔ fail-fast: structural modification after iterator creation
    @Test
    void whenModifyAfterIteratorCreatedThenFailFast() {
        Iterator<Integer> iterator = list.iterator();
        list.add(3);

        assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    // ✔ проверка NoSuchElementException
    @Test
    void whenNextFromEmptyIteratorThenNoSuchElement() {
        SimpleLinked<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenNextBeyondLastElementThenNoSuchElement() {
        Iterator<Integer> it = list.iterator();
        it.next(); // 1
        it.next(); // 2
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    // ✔ проверка размера
    @Test
    void whenAddManyElementsThenSizeIsCorrect() {
        list.add(3);
        list.add(4);
        list.add(5);
        assertThat(list).hasSize(5);
    }

    // ✔ проверка корректной последовательности при большом числе элементов
    @Test
    void whenAddTenElementsThenIteratorReturnsAll() {
        SimpleLinked<Integer> list2 = new SimpleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list2.add(i);
        }

        Iterator<Integer> it = list2.iterator();
        for (int i = 0; i < 10; i++) {
            assertThat(it.next()).isEqualTo(i);
        }
        assertThat(it.hasNext()).isFalse();
    }

    // ✔ hasNext() не двигает указатель
    @Test
    void whenCallHasNextMultipleTimesPointerDoesNotMove() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(1);
    }
}
