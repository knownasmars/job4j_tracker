package ru.job4j.oop;

public class Jukebox {
    public void music(int position) {
        if (position == 1) {
            System.out.println("Пусть бегут неуклюже");
        } else if (position == 2) {
            System.out.println("Спокойной ночи");
        } else {
            System.out.println("Песня не найдена");
        }
    }

    public static void main(String[] args) {
        Jukebox songCheburashka = new Jukebox();
        int songNumber = 1;
        songCheburashka.music(songNumber);
        songNumber = 2;
        songCheburashka.music(songNumber);
        songNumber = 0;
        songCheburashka.music(songNumber);
    }
}
