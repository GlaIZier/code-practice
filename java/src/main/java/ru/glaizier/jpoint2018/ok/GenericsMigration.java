package ru.glaizier.jpoint2018.ok;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class GenericsMigration {

    // what can be assigned?
    public static void main(String[] args) {
        List<?> l = Arrays.asList("1", 2, 3.0);
        List<Comparable> l2 = Arrays.asList("1", 2, 3.0);
        List<Serializable> l3 = Arrays.asList("1", 2, 3.0);
        Arrays.asList("1", 2, 3.0);
        Arrays.asList("1", 2, 3.0);
        Arrays.asList("1", 2, 3.0);
        Arrays.asList("1", 2, 3.0);
    }

}
