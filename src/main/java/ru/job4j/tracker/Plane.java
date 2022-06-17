package ru.job4j.tracker;

public class Plane implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " перевожу пассажиров по странам.");
    }
}
