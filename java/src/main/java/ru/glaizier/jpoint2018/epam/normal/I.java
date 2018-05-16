package ru.glaizier.jpoint2018.epam.normal;

public class I {
    public static void main(String[] args) {
        int i = 1;
        int j = i++; // 2, 1
        if ((i == ++j) | (i++ == j)) {
//            i +- j; // not a statement
        }
        System.out.println(i);
    }
}

