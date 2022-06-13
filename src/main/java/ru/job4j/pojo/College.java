package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setSurname("Suleimanov");
        student.setGroup("№ 001");
        student.setAdmissionDate(new Date());
        System.out.println(student.getSurname() + " учится в группе " + student.getGroup() + " с " + student.getAdmissionDate());
    }
}
