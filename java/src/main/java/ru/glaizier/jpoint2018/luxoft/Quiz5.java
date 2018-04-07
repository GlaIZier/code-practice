package ru.glaizier.jpoint2018.luxoft;

import java.util.HashSet;

public class Quiz5 {
    public static void main(String[] args) {
        HashSet<Short> set = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i - 1); // i - 1 casts to int => hence, it is not deleted from
        }
        System.out.println(set.size()); // 100
    }
}
