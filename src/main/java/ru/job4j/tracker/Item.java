package ru.job4j.tracker;

import java.time.LocalDateTime;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Item implements Comparable<Item> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    @NonNull
    private String name;

    @EqualsAndHashCode.Exclude
    private LocalDateTime created = LocalDateTime.now();

    public Item(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare(this.id, o.id);
    }
}