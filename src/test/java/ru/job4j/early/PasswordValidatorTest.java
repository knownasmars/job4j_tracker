package ru.job4j.early;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.job4j.early.PasswordValidator.*;

public class PasswordValidatorTest {

    @Test
    public void whenPasswordIsNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    validate(null);
                });
        Assertions.assertEquals(
                "The password is null",
                thrown.getMessage());
    }

    @Test
    public void whenPasswordHasWrongLength() {
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    lengthVal("123");
                });
        Assertions.assertEquals(
                "Password's length is out of range",
                thrown.getMessage());
    }

    @Test
    public void whenPasswordIsQwerty() {
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    substringVal("qwerty");
                });
        Assertions.assertEquals(
                "Please, choose another password",
                thrown.getMessage());
    }

    @Test
    public void whenPasswordHasNoDigis() {
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    digitVal("abcdefghi");
                });
        Assertions.assertEquals(
                "No digits in password. Try again please",
                thrown.getMessage());
    }

    @Test
    public void whenPasswordHasNoLetterNoDigits() {
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    symbolVal("123456abcdef");
                });
        Assertions.assertEquals(
                "No symbols in password. Try again please",
                thrown.getMessage());
    }

    @Test
    public void whenPasswordUpperVal() {
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    upperLowerVal("AAAAA");
                });
        Assertions.assertEquals(
                "Please don't choose only UpperCase or LowerCase",
                thrown.getMessage());
    }
}