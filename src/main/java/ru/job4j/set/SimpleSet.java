package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(1);

    @Override
    public boolean add(T value) {
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            T tmp = iterator.next();
            if ((value == null && tmp == null)
                    || tmp.equals(value)) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            T tmp = iterator.next();
            if ((value == null && tmp == null)
                    || tmp.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
