package ru.glaizier.jpoint2018.epam.normal;

/**
 * @author GlaIZier
 */
// What will be printed
public class FoobarBaz {

    public static void main(String[] args) {
        int foobar = 0;
        int baz = 10;
        do {
            baz--;
            ++foobar;
        } while (foobar < 5);
        System.out.println(foobar + ", " + baz); // 5, 5
    }
}
