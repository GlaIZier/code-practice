package ru.glaizier.isnumberpoweroftwo;

public class IsNumberPowOfTwo {

    public static void main(String[] args) {
        System.out.println("testNumberLogarithmic(1) = " + testNumberLogarithmic(1));
        System.out.println("testNumberLogarithmic(2) = " + testNumberLogarithmic(2));
        System.out.println("testNumberLogarithmic(4) = " + testNumberLogarithmic(4));
        System.out.println("testNumberLogarithmic(32) = " + testNumberLogarithmic(32));
//        System.out.println("testNumberLogarithmic(-1) = " + testNumberLogarithmic(-1));
        System.out.println("testNumberLogarithmic(0) = " + testNumberLogarithmic(0));
        System.out.println("testNumberLogarithmic(Integer.MAX_VALUE) = " + testNumberLogarithmic(Integer.MAX_VALUE));
//        System.out.println("testNumberLogarithmic(Integer.MAX_VALUE + 1) = " + testNumberLogarithmic(Integer.MAX_VALUE + 1));

        System.out.println("testNumberLinear(1) = " + testNumberLinear(1));
        System.out.println("testNumberLinear(2) = " + testNumberLinear(2));
        System.out.println("testNumberLinear(3) = " + testNumberLinear(3));
        System.out.println("testNumberLinear(Integer.MAX_VALUE) = " + testNumberLinear(Integer.MAX_VALUE));
    }

    // Can you do this with recursion?
    public static boolean testNumberLogarithmic(int number) {
        if (number < 0)
            throw new UnsupportedOperationException();

        // because 2^31 - is negative value
        double maxNumberToPow = Math.pow(2, 30);
        for (int testValue = 1; testValue < maxNumberToPow; testValue *= 2) {
            if (testValue == number)
                return true;
        }
        return number == maxNumberToPow;
    }

    public static boolean testNumberLinear(int number) {
        if (number < 0)
            throw new UnsupportedOperationException();
        return (number & number - 1) == 0;
    }
}
