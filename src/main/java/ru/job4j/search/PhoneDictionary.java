package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> cmpName = person -> person.getName().contains(key);
        Predicate<Person> cmpSurname = person -> person.getSurname().contains(key);
        Predicate<Person> cmpPhone = person -> person.getPhone().contains(key);
        Predicate<Person> cmpAddress = person -> person.getAddress().contains(key);
        Predicate<Person> combine = cmpName.or(cmpSurname.or(cmpPhone.or(cmpAddress)));
        ArrayList<Person> result = new ArrayList<Person>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}