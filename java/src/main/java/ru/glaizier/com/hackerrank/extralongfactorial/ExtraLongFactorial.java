package ru.glaizier.com.hackerrank.extralongfactorial;

import java.math.BigInteger;

public class ExtraLongFactorial {

    public static BigInteger calculate(int n) {
        assert n > 0;
        BigInteger result = new BigInteger(String.valueOf(n));
        for (n = n - 1; n > 0; n--)
            result = result.multiply(BigInteger.valueOf(n));
        return result;
    }

    public static BigInteger calculateRecursive(int n) {
        assert n > 0;
        if (n == 1)
            return new BigInteger(String.valueOf("1"));
        return new BigInteger(String.valueOf(n)).multiply(calculateRecursive(n - 1));
    }

    public static BigInteger calculateTailRecursive(int n) {
        assert n > 0;
        return calculateTailRecursive(new BigInteger("1"), n);
    }

    private static BigInteger calculateTailRecursive(BigInteger acc, int n) {
        return n == 1 ? acc :
                calculateTailRecursive(acc.multiply(new BigInteger(String.valueOf(n))), n - 1);
    }


}
