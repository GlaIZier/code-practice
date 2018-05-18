package ru.glaizier.jpoint2018.luxoft.day1;

import java.util.stream.Stream;

public class Quiz4 {
    public static void main(String[] args) {
        Stream<String> s = Stream.of("A", "B");
        System.out.println("1");
        s.peek(System.out::println);
        System.out.println("2");
        s.forEach(System.out::println); // runtime exception. Was already consumed
    }
}
