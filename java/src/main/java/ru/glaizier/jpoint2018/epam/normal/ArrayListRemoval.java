package ru.glaizier.jpoint2018.epam.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GlaIZier
 */
// What will be printed
public class ArrayListRemoval {
    public static void main(String[] args) {
        List s1 = new ArrayList(); // list of objects
        s1.add("a"); // "a"s are the same as Java use a string pool for constants
        s1.add("b");
        s1.add("c");
        s1.add("a");
        // remove the first occurrence of a
        if (s1.remove("a")) {
            // remove the second a
            if (s1.remove("a")) {
                // remove b and c remains
                s1.remove("b");
            } else {
                s1.remove("c");
            }
        }
        System.out.println(s1); // c
    }
}
