package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("knownasmars@gmail.com", "Suleimanov Mars Eduardovich");
        for (String k : map.keySet()) {
            System.out.println(k + " - " + map.get(k));
        }
    }
}
