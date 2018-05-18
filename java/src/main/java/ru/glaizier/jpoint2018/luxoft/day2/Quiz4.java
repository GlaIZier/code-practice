package ru.glaizier.jpoint2018.luxoft.day2;

import java.util.Optional;

/**
 * @author GlaIZier
 */
public class Quiz4 {

    // What will be printed?
    public static void main(String[] args) {
       int n = 1;
        Integer result = Optional.of(n++)
//            .map(i -> i + n) // won't compile because n is needed to be effectively final
            .orElse(-1);
        System.out.println(result);
    }
}
