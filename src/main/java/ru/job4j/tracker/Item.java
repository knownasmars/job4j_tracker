package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.*;

@Data
@EqualsAndHashCode
public class Item implements Comparable<Item> {

    private int id;

    private String name;

    @EqualsAndHashCode.Exclude
    private LocalDateTime created = LocalDateTime.now();
    @EqualsAndHashCode.Exclude
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy");

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare(this.id, o.id);
    }
}