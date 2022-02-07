package ru.job4j.tree;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        Assert.assertTrue(tree.add(1, 2));
        Assert.assertTrue(tree.add(1, 3));
        Assert.assertTrue(tree.add(1, 4));
        Assert.assertTrue(tree.add(4, 5));
        Assert.assertTrue(tree.add(5, 6));
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }

    @Test
    public void whenIsNotBinaryTree() {
        Tree<Integer> tree = new SimpleTree<>(1);
        Assert.assertTrue(tree.add(1, 2));
        Assert.assertTrue(tree.add(1, 3));
        Assert.assertTrue(tree.add(1, 4));
        Assert.assertTrue(tree.add(4, 5));
        Assert.assertTrue(tree.add(5, 6));
        Assert.assertTrue(tree.add(6, 7));
        Assert.assertTrue(tree.add(6, 8));
        Assert.assertTrue(tree.add(6, 9));
        Assert.assertFalse(tree.isBinary());
    }

    @Test
    public void whenIsBinaryTree() {
        Tree<Integer> tree = new SimpleTree<>(1);
        Assert.assertTrue(tree.add(1, 2));
        Assert.assertTrue(tree.add(1, 4));
        Assert.assertTrue(tree.add(4, 5));
        Assert.assertTrue(tree.add(5, 6));
        Assert.assertTrue(tree.add(6, 7));
        Assert.assertTrue(tree.add(6, 9));
        Assert.assertTrue(tree.isBinary());
    }

}