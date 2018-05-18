package ru.glaizier.jpoint2018.luxoft.day1;

import java.util.Arrays;

public class Quiz2 {
    public static void main(String[] args) {
        String[] strings = {"1", "2"};
        Object[] objects = strings;
        objects[0] = 0;
        System.out.println(Arrays.toString(objects)); // Runtime exception
    }
}
