package ru.job4j.tracker;

public class PolyUsage {
    public static void main(String[] args) {
        Vehicle bus = new Autobus();
        Vehicle plane = new Plane();
        Vehicle train = new Train();
        Vehicle[] vehicles = new Vehicle[]{bus, plane, train};
        for (Vehicle v : vehicles) {
            v.move();
        }
    }
}
