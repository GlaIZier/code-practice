package ru.glaizier.jpoint2018.epam.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author GlaIZier
 */
public class LazyStreamSource {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Stream<String> stream = list.stream();
        list.add("3");
        stream.forEach(System.out::println); // will print 1 2 3
    }
}
