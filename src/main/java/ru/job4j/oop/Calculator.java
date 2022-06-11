package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public int divide(int y) {
        return y / x;
    }

    public int multiply(int a) {
        return x * a;
    }

    public int sumAllOperation(int y) {
        return sum(y) + divide(y) + minus(y) + multiply(y);
    }

    public static int minus(int y) {
        return y - x;
    }

    public static int sum(int y) {
        return x + y;
    }

    public static void main(String[] args) {
        int result = Calculator.sum(10);
        int resDif = Calculator.minus(12);
        Calculator multiplied = new Calculator();
        int multipliedResult = multiplied.multiply(15);
        Calculator divided = new Calculator();
        int dividedResult = divided.divide(22);
        Calculator operationSum = new Calculator();
        int operationsResult = operationSum.sumAllOperation(10);
        System.out.println(result);
        System.out.println(resDif);
        System.out.println(multipliedResult);
        System.out.println(dividedResult);
        System.out.println(operationsResult);
    }
}