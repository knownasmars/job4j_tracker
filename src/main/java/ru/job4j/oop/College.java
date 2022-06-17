package ru.job4j.oop;

public class College {
    public static void main(String[] args) {
        Freshman freshman = new Freshman();
        Student student = freshman;
        Object obj = freshman;
        Object ofresh = new Freshman();
        Student fromObj = (Student) ofresh;
    }
}
