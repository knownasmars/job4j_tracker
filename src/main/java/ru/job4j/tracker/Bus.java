package ru.job4j.tracker;

public class Bus implements Transport {

    @Override
    public void toRide() {
    }

    @Override
    public void passengers(int passengers) {
        passengers = passengers + 1;
    }

    @Override
    public int getFueled(int fuel) {
        int fuelPrice = fuel * 50;
        return fuelPrice;
    }
}
