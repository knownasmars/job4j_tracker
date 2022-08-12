package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rst = 0;
        for (int i = 1; i < Math.min(left.length(), right.length()); i++) {
            rst = Character.compare(right.charAt(1), left.charAt(1));
            if (rst == 0) {
                if (left.length() == right.length()) {
                    rst = Character.compare(left.charAt(i), right.charAt(i));
                    continue;
                }
                return Integer.compare(left.length(), right.length());
            }
            return Character.compare(right.charAt(i), left.charAt(i));
        }
        rst = Character.compare(left.charAt(left.length() - 1), right.charAt(right.length() - 1));
        return rst;
    }
}