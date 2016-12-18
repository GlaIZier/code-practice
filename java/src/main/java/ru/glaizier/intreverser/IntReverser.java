package ru.glaizier.intreverser;

public class IntReverser {

    /**
     * 321 / 10 -> 1; 32
     * 32 / 10 -> 2; 3
     * 3 / 10 -> 3; 0
     */
    public static int reverse(int n) {
        int reversed = 0;
        int integer = n;
        int remainder;
        while (integer != 0) {
            remainder = Math.abs(integer % 10);
            integer = integer / 10;
            reversed = reversed * 10 + remainder;
            if (reversed < 0)
                throw new IllegalArgumentException("Number is too big for int type to reverse!");
        }
        return (n < 0) ? -reversed : reversed;
    }
}
