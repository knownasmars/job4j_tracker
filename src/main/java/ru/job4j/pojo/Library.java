package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book bookForKids = new Book("3 мушкетера", 300);
        Book bookForStudents = new Book("Высшая математика", 99999);
        Book bookForCoders = new Book("Clean Code", 1000);
        Book bookForParents = new Book("Уход за детьми", 500);
        Book[] books = new Book[4];
        books[0] = bookForKids;
        books[1] = bookForStudents;
        books[2] = bookForCoders;
        books[3] = bookForParents;
        for (int i = 0; i < books.length; i++) {
            Book bo = books[i];
            System.out.println(bo.getName() + " содержит " + bo.getPages());
        }
        Book tmp = books[3];
        books[3] = books[0];
        books[0] = tmp;
        for (int i = 0; i < books.length; i++) {
            Book bo = books[i];
            System.out.println(bo.getName() + " содержит " + bo.getPages());
        }
        for (int i = 0; i < books.length; i++) {
            Book bo = books[i];
            if ("Clean Code".equals(bo.getName())) {
                System.out.println(bo.getName() + " - " + bo.getPages());
            }
        }
    }
}
