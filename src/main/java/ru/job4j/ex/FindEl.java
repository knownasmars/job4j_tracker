package ru.job4j.ex;

import javax.sql.rowset.serial.SerialStruct;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                rsl = i;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Error! There's no value in array");
        }
        return rsl;
    }

    public static void main(String[] args) {
        try {
            int i = indexOf(new String[]{"1", "2", "3"}, "10");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}