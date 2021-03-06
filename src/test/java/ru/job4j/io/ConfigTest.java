package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./Data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWithoutPair1() {
        String path = "./Data/without_pair1.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWithoutPair2() {
        String path = "./Data/without_pair2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWithoutPair3() {
        String path = "./Data/without_pair3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithCommentAndEmptyString() {
        String path = "./Data/pair_with_cooment_and_emptyString.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

}