package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void whenAddRepeatValue() {
        Map<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("KING", 100));
        Assert.assertFalse(map.put("BLAKE", 200));
        assertThat(map.get("KING"), is(100));
        assertThat(map.get("BLAKE"), is(100));
    }

    @Test
    public void whenAdd7ValueThenExpand() {
        Map<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("A", 100));
        Assert.assertTrue(map.put("B", 200));
        Assert.assertTrue(map.put("C", 300));
        Assert.assertTrue(map.put("D", 400));
        Assert.assertTrue(map.put("E", 500));
        Assert.assertTrue(map.put("F", 600));
        Assert.assertTrue(map.put("G", 700));
    }

    @Test
    public void whenTestingIteratorThenOk() {
        Map<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("1", 1));
        Assert.assertTrue(map.put("2", 2));
        Assert.assertTrue(map.put("3", 3));
        int index = 1;
        Iterator<String> iterator = map.iterator();
        while (iterator.hasNext()) {
            Assert.assertEquals(iterator.next(), map.get(Integer.toString(index)).toString());
            index++;
        }
    }

    @Test
    public void whenAddOneFindOneThenOk() {
        Map<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("1", 1));
        assertThat(map.get("1"), is(1));
    }

    @Test
    public void whenAddOneFindTwoThenNoOk() {
        Map<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("1", 1));
        assertNull(map.get("2"));
    }

    @Test
    public void whenAddOneRemoveOneThenOk() {
        Map<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("1", 1));
        Assert.assertTrue(map.put("2", 2));
        assertTrue(map.remove("1"));
    }

    @Test
    public void whenAddOneRemoveThreeThenNoOk() {
        Map<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("1", 1));
        Assert.assertTrue(map.put("2", 2));
        assertFalse(map.remove("3"));
    }
}