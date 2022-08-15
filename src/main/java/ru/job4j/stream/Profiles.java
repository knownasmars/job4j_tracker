package ru.job4j.stream;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Profiles {

    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .collect(toList());
    }
}