package ru.glaizier.swaptwonumbers;

import ru.glaizier.util.Pair;

public class TwoNumbersSwapper {

    public static Pair<Integer, Integer> swap(int a, int b) {
        a = a - b;
        b = a + b;
        a = b - a;
        return new Pair<>(a, b);
    }

    public static Pair<Integer, Integer> bitOperationsSwap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return new Pair<>(a, b);
    }

    // also u can use xor in registers in assembler if u can do assembler paste

}
