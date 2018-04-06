package ru.glaizier.com.hackerrank.minutestohours;

import ru.glaizier.util.Pair;

public class MinutesToHours {

    public static Pair<Integer, Integer> convert(int minutes) {
        if (minutes < 0)
            throw new IllegalArgumentException("Minutes should be positive!");

        int hours = 0;
        while (minutes > 59) {
            minutes -= 60;
            hours++;
        }
        return new Pair<>(hours, minutes);
    }

}
