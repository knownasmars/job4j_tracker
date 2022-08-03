package ru.job4j.tracker;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemSorterByNameTest {
    @Test
    public void whenSortedAscByName() {
        List<Item> items = new ArrayList<>(
                List.of(
                        new Item(1, "Mars"),
                        new Item(2, "Andrey"),
                        new Item(3, "Artem")
                )
        );
        Collections.sort(items, new ItemAscByName());
        List<Item> expected = new ArrayList<>(
                List.of(
                        new Item(2, "Andrey"),
                        new Item(3, "Artem"),
                        new Item(1, "Mars")
                )
        );
        assertThat(items).isEqualTo(expected);
    }

    @Test
    public void whenSortedDescByName() {
        List<Item> items = new ArrayList<>(
                List.of(
                        new Item("Andrey"),
                        new Item("Mars"),
                        new Item("Artem")
                )
        );
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = new ArrayList<>(
                List.of(
                        new Item("Mars"),
                        new Item("Artem"),
                        new Item("Andrey")
                )
        );
        assertThat(items).isEqualTo(expected);
    }
}