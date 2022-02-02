package ru.job4j.map;

import ru.job4j.linklist.SimpleLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        int oldCount = table.length;
        capacity = oldCount << 1;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        table = newTable;
        for (int i = 0; i < oldCount; i++) {
            if (oldTable[i] == null) {
                continue;
            }
            int index = indexFor(hash(oldTable[i].key.hashCode()));
            table[index] = new MapEntry<>(oldTable[i].key, oldTable[i].value);
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] == null ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            table[index] = null;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private int expectedModCount = modCount;
            private int tmpCount = 0;
            private int index = 0;
            private MapEntry<K, V> cursor = table[index];

            @Override
            public boolean hasNext() {
                while (tmpCount < count
                        && index < table.length
                        && (cursor = table[index]) == null) {
                    index++;
                }
                if (cursor != null) {
                    tmpCount++;
                }
                return tmpCount < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[index++].key;
            }

        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
