package ru.glaizier.util;

public abstract class Math {

    public static boolean isPrime(long n) {
        if (n <= 1)
            return false;
        for (int check = 2; check * check <= n; check++) {
            if (n % check == 0)
                return false;
        }
        return true;
    }

    // Can you do this with recursion?
    public static boolean isPowerOfTwoLogarithmic(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        // because 2^31 - is negative value
        double maxNumberToPow = java.lang.Math.pow(2, 30);
        for (int testValue = 1; testValue < maxNumberToPow; testValue *= 2) {
            if (testValue == n)
                return true;
        }
        return n == maxNumberToPow;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        return (n & n - 1) == 0;
    }

    public static boolean isPowerOfThree(int n) {
        return false;
    }

}
