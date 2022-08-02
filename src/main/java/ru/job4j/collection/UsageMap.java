package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("knownasmars@gmail.com", "Suleimanov Mars Eduardovich");
        map.put("known123asmars@gmail.com", "Ivanov Mars Eduardovich");
        map.put("kn53523ownasmars@gmail.com", "Suleimanov Dima Eduardovich");
        map.put("knownasmars@gmail.com", "Suleimanov Mars Eduardovich");
        for (String k : map.keySet()) {
            System.out.println(k + " - " + map.get(k));
        }
    }
}
