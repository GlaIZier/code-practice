package ru.glaizier.prevnextprimenumber;

import ru.glaizier.util.Math;
import ru.glaizier.util.Tuple;

public class PrevNextPrimeNumber {

    public static Tuple<Long, Long> get(long n) {
        if (n <= 1)
            return new Tuple<Long, Long>(null, (long) 2);
        Long prev = findPrevPrime(n);
        Long next = findNextPrime(n);
        return new Tuple<Long, Long>(prev, next);
    }

    private static Long findPrevPrime(long n) {
        Long prev = n - 1;
        while (!Math.isPrime(prev) && prev > 1)
            prev--;
        return (prev == 1) ? null : prev;
    }

    private static Long findNextPrime(long n) {
        Long next = n + 1;
        while (!(Math.isPrime(next) && next <= Long.MAX_VALUE))
            next++;
        return (next == Long.MAX_VALUE) ? null : next;
    }


}
