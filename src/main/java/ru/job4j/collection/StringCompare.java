package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rst = 0;
        if (left.equals(right)) {
            return 0;
        }
        int size = Math.max(left.length(), right.length());
        for (int i = 0; i < size - 1; i++) {
            rst += Character.compare(left.charAt(i), right.charAt(i));
            if (rst != 0) {
                return rst;
            }
        }
        for (int i = 0; i < size - 1; i++) {
            rst += Integer.compare(left.length(), right.length());
            if (rst != 0) {
                return rst;
            }
        }
        return rst;
    }
}