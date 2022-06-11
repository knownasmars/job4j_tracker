package ru.job4j.oop;

import java.lang.constant.Constable;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {

    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активность: " + active);
        System.out.println("Статус: " + status);
        System.out.println("Сообщение: " + message);
    }

    public static void main(String[] args) {
        Error errorDefault = new Error();
        errorDefault.printInfo();
        Error errorFull = new Error(true, 10, "System works");
        errorFull.printInfo();
    }
}
