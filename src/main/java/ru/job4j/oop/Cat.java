package ru.job4j.oop;

public class Cat {

    private String food;
    private String name;

    public void giveNick(String nick) {
        this.name = nick;
    }

    public void show() {
        System.out.println("Котик " + this.name + " ел " + this.food);
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public static void main(String[] args) {
        Cat gav = new Cat();
        gav.eat("kotleta");
        Cat black = new Cat();
        black.eat("fish");
        gav.giveNick("гав");
        black.giveNick("черныш");
        gav.show();
        black.show();
    }
}