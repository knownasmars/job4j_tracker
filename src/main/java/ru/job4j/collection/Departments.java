package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String str : deps) {
            String start = "";
            for (String el : str.split("/")) {
                if (start == "") {
                    start = el;
                    tmp.add(start);
                    continue;
                }
                start = start + "/" + el;
                tmp.add(start);
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        Collections.sort(orgs);
    }

    public static void sortDesc(List<String> orgs) {
        orgs.sort(new DepDescComp());
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList(
                "K1/SK1/SSK1",
                "K1/SK2",
                "K1",
                "K2/SK1",
                "K2"
        );
        sortDesc(input);
        for (String s : input) {
            System.out.println(s);
        }
    }
}