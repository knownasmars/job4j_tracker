package ru.job4j.tracker;

public interface Transport {

    void toRide();

    void passengers(int passengers);

    int getFueled(int fuel);
}
