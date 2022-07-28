package ru.job4j.collection;

import java.util.ArrayList;

public class UsageArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Марс");
        list.add("Андрей");
        list.add("Пётр");
        for (String s : list) {
            System.out.println(s);
        }
    }
}