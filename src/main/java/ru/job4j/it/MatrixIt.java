package ru.job4j.it;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        while (row < data.length && !rsl) {
            if (data[row].length == 0) {
                row++;
                continue;
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][column];
        column++;
        if (column >= data[row].length) {
            column = 0;
            row++;
        }
        return rsl;
    }

    @Override
    public void remove() {
    }
}
