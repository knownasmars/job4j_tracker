package ru.job4j.lambda;

interface MyInterface {
    double getsPiVlue();
}

class Main {
    public static void main(String[] args) {
        MyInterface pi;
        pi = () -> 3.14;
        System.out.println("Я сам сделал лямбда " + pi.getsPiVlue());
    }
}