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


}
