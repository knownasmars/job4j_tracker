package ru.job4j.stream;

import java.util.List;
import static java.util.stream.Collectors.*;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        return products.stream()
                .filter(p -> p.getStandard() - p.getActual() <= 3)
                .filter(p -> p.getStandard() - p.getActual() >= 0)
                .map(p -> new Label(p.getName(), p.getPrice() * 0.5f).toString())
                .collect(toList());
    }
}