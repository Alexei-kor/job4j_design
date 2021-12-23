package ru.job4j.linklist;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() throws NoSuchElementException {
        if (in.emptyStack() && out.emptyStack()) {
            throw new NoSuchElementException("No such element in queue.");
        }
        if (out.emptyStack()) {
            while (!in.emptyStack()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}
