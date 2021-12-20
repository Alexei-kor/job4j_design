package ru.job4j.linklist;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() throws NoSuchElementException {
        if (in.emptyStack()) {
            throw new NoSuchElementException("No such element in queue.");
        }
        T rsl = in.pop();
        out.pushEnd(rsl);
        return rsl;
    }

    public void push(T value) {
        in.pushEnd(value);
    }
}
