package ru.glaizier.jpoint2018.epam.normal;

// What will be printed?
// Answer: won't compile
public class I {
    public static void main(String[] args) {
        int i = 1;
        int j = i++; // 2, 1
        if ((i == ++j) | (i++ == j)) {
//            i +- j; // not a statement
        }
        System.out.println(i);
        test();
    }

    // wasn't a task
    private static void test() {
        int i = 1;
        int j = i++; // 2, 1
        boolean b = (i == ++j) | (i++ == j);
        System.out.println("b = " + b);
        System.out.println("true | true = " + (true | true));
        System.out.println("false | true = " + (false | true));
        System.out.println("false | false = " + (false | false));
    }
}

