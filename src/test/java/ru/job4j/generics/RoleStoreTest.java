package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsReadPilot() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pilot"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Pilot"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pilot"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsPilot() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pilot"));
        store.add(new Role("1", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Pilot"));
    }

    @Test
    public void whenReplaceThenRolenameIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pilot"));
        store.replace("1", new Role("1", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Driver"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pilot"));
        store.replace("10", new Role("10", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Pilot"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pilot"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenUsernameIsPilot() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pilot"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName(), is("Pilot"));
    }
}