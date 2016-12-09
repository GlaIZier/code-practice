package ru.glaizier.swaptwonumbers;

import ru.glaizier.util.Tuple;

public class TwoNumbersSwapper {

    public static Tuple<Integer, Integer> swap(int a, int b) {
        a = a - b;
        b = a + b;
        a = b - a;
        return new Tuple<Integer, Integer>(a, b);
    }

    public static Tuple<Integer, Integer> bitOperationsSwap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return new Tuple<Integer, Integer>(a, b);
    }

}
