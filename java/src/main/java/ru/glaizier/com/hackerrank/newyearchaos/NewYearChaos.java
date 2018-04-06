package ru.glaizier.com.hackerrank.newyearchaos;

/**
 * https://www.hackerrank.com/challenges/new-year-chaos
 * O(n^2) =(
 */
public class NewYearChaos {

    public static int getSwaps(int[] a) {
        assert a != null;

        for (int i = 0; i < a.length; i++) {
            // + 1 because start array index is 0 not 1
            if (Math.abs(a[i] - (i + 1)) > 2)
                return -1;
        }

        int swaps = 0;
        // bubble sort
        for (int out = 0; out < a.length; out++) {
            boolean swapped = false;
            for (int in = 1; in < a.length - out; in++) {
                if (a[in - 1] > a[in]) {
                    int tmp = a[in - 1];
                    a[in - 1] = a[in];
                    a[in] = tmp;
                    swaps++;
                    swapped = true;
                }
            }
            // sort is finished
            if (!swapped)
                break;
        }
        return swaps;
    }


}
