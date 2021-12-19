package ru.job4j.linklist;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T rsl = in.pop();
        out.pushEnd(rsl);
        return rsl;
    }

    public void push(T value) {
        in.pushEnd(value);
    }
}
