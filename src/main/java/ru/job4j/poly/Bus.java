package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void toRide() {
        passengers(15);
        System.out.println(
                "Чтобы заправить автобус надо заплатить " + getFueled(15));
    }

    @Override
    public void passengers(int passengers) {
        System.out.println("В автобус помещается: " + passengers);
    }

    @Override
    public int getFueled(int fuel) {
        return fuel * 50;
    }
}
