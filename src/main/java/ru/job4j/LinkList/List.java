package ru.job4j.LinkList;

public interface List<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
