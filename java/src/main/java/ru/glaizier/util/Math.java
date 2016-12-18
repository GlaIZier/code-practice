package ru.glaizier.util;

public abstract class Math {

    public static boolean isPrime(long n) {
        if (n <= 1)
            return false;
        long lastCheck = (long) java.lang.Math.sqrt(n);
        for (int check = 2; check <= lastCheck; check++) {
            if (n % check == 0)
                return false;
        }
        return true;
    }

    // Can you do this with recursion?
    @SuppressWarnings("StatementWithEmptyBody")
    public static boolean isPowerOfTwoLogarithmic(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        int testValue = 1;
        for (; testValue <= Integer.MAX_VALUE / 2 && testValue < n; testValue *= 2) {}
        return testValue == n;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        return (n & n - 1) == 0;
    }

    public static boolean isPowerOfThree(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        int testValue = 1;
        while (testValue <= Integer.MAX_VALUE / 3 && testValue < n)
            testValue *= 3;
        return testValue == n;
    }

}
