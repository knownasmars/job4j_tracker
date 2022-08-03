package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemSorter {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item(3, "Z"),
                new Item(1, "B"),
                new Item(2, "C"),
                new Item(4, "A")
        );
        System.out.println(items);
        Collections.sort(items, new ItemDescByName());
        System.out.println(items);
        Collections.sort(items, new ItemAscByName());
        System.out.println(items);
        Collections.sort(items);
        System.out.println(items);
    }
}