package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }

        int hash = hash(Objects.hashCode(key));
        int index = indexFor(hash);

        if (table[index] != null) {
            return false;
        }

        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    @Override
    public V get(K key) {
        int hash = hash(Objects.hashCode(key));
        int index = indexFor(hash);

        MapEntry<K, V> entry = table[index];
        if (entry == null) {
            return null;
        }

        if (Objects.hashCode(entry.key) == Objects.hashCode(key)
                && Objects.equals(entry.key, key)) {
            return entry.value;
        }

        return null;
    }

    @Override
    public boolean remove(K key) {
        int hash = hash(Objects.hashCode(key));
        int index = indexFor(hash);

        MapEntry<K, V> entry = table[index];
        if (entry == null) {
            return false;
        }

        if (Objects.hashCode(entry.key) == Objects.hashCode(key)
                && Objects.equals(entry.key, key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            int expectedModCount = modCount;
            int cursor = 0;
            int returned = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < capacity && table[cursor] == null) {
                    cursor++;
                }
                return cursor < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        count = 0;

        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
