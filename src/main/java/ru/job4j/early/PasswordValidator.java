package ru.job4j.early;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("The password is null");
        }
        return "Password is Valid";
    }

    public static void lengthVal(String password) {
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password's length is out of range");
        }
    }

    public static void substringVal(String password) {
        if (password.equals("12345") || password.equals("qwerty")
                || password.equals("password") || password.equals("admin")
                || password.equals("user")) {
            throw new IllegalArgumentException("Please, choose another password");
        }
    }

    public static void digitVal(String password) {
        int digits = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                digits++;
            }
        }
        if (digits < 1) {
            throw new IllegalArgumentException("No digits in password. Try again please");
        }
    }

    public static void symbolVal(String password) {
        int symbols = 0;
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isLetterOrDigit(password.charAt(i))) {
                symbols++;
            }
        }
        if (symbols < 1) {
            throw new IllegalArgumentException("No symbols in password. Try again please");
        }
    }

    public static void upperLowerVal(String password) {
        if (password.equals(password.toUpperCase()) || password.equals(password.toLowerCase())) {
            throw new IllegalArgumentException("Please don't choose only UpperCase or LowerCase");
        }
    }
}
