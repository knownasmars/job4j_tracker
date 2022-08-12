package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rst = 0;
//                "K1/SK1"
//                "K2",
        for (int i = 1; i < Math.min(left.length(), right.length()); i++) {
            rst = Character.compare(right.charAt(i), left.charAt(i));
            if (rst == 0) {
                if (left.length() == right.length()) {
                    return Character.compare(right.charAt(i + 1), left.charAt(i + 1));
                }
//                continue;
                return Integer.compare(left.length(), right.length());
            }
            return Character.compare(right.charAt(i), left.charAt(i));
        }
        rst = Integer.compare(left.length(), right.length());
        return rst;
    }
}