package ru.job4j.listiter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 4));
        ListUtils.addAfter(list, 0, 2);
        assertThat(list, is(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.removeIf(list, e -> e > 3);
        assertThat(list, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.replaceIf(list, e -> e > 3, 77);
        assertThat(list, is(Arrays.asList(1, 2, 3, 77, 77, 77, 77)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.removeAll(list, Arrays.asList(3, 5, 7));
        assertThat(list, is(Arrays.asList(1, 2, 4, 6)));
    }

}