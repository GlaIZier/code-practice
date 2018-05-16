package ru.glaizier.jpoint2018.epam.easy;

/**
 * @author GlaIZier
 */
public class DefaultNumberType {
    public static void main(String[] args) {
        byte b = 8;
        method(b);
//        method(7); 7 is int by default, not byte, so it won't compile
    }

    private static void method(byte b) {
        System.out.println("b");
    }
}
