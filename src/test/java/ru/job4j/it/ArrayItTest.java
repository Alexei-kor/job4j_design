package ru.job4j.it;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ArrayItTest {

    @Test
    public void whenMultiCallhasNextThenTrue() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        Assert.assertEquals((long) it.next(), 1L);
        Assert.assertEquals((long) it.next(), 2L);
        Assert.assertEquals((long) it.next(), 3L);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenException() {
        ArrayIt it = new ArrayIt(new int[]{});
        it.next();
    }

}