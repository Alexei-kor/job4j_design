package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private Object[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T old = get(index);
        container[index] = newValue;
        return old;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        modCount++;
        T old = get(index);
        System.arraycopy(container, index + 1, container, index, size - index);
        container[size] = null;
        size--;
        return old;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return (T) container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(point++);
            }

        };
    }

    public void grow() {
        container = Arrays.copyOf(container, container.length * 2);
    }

}
