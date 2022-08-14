package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rst = 0;
        String[] leftWord = left.split("/");
        String[] rightWord = right.split("/");
        rst = rightWord[0].compareTo(leftWord[0]);
        return rst != 0 ? rst : left.compareTo(right);
    }
}