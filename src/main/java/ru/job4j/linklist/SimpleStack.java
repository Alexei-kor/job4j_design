package ru.job4j.linklist;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public void pushEnd(T value) {
        linked.add(value);
    }

    public boolean emptyStack() {
        return !linked.iterator().hasNext();
    }
}
