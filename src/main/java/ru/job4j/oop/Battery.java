package ru.job4j.oop;

public class Battery {
    private int load;

    public Battery(int load) {
        this.load = load;
    }

    public int getLoad() {
        return load;
    }

    public void exchange(Battery another) {
        another.load = another.load + this.load;
        this.load -= this.load;
    }

    public static void main(String[] args) {
        Battery battery = new Battery(30);
        Battery battery2 = new Battery(50);
        System.out.println("Заряд батареи 1: " + battery.getLoad());
        System.out.println("Заряд батери 2: " + battery2.getLoad());
        battery.exchange(battery2);
        System.out.println("Заряд батареи 1: " + battery.getLoad());
        System.out.println("Заряд батери 2: " + battery2.getLoad());
    }
}
