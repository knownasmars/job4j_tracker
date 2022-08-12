package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rst = 0;
//                "K1",
//                "K1/SK1/,
//                "K2"
        for (int i = 0; i < Math.min(left.length(), right.length()); i++) {
            rst = Character.compare(right.charAt(i), left.charAt(i));
            if (rst != 0) {
                return Character.compare(right.charAt(i), left.charAt(i));
            }
            rst = Character.compare(left.charAt(i), right.charAt(i));
        }
        rst = Integer.compare(left.length(), right.length());
        return rst;
    }
}