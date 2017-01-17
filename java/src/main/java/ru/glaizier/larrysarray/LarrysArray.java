package ru.glaizier.larrysarray;


// https://www.hackerrank.com/challenges/larrys-array
public class LarrysArray {

    // Mathematic solution. Sortable when number of inversions is even
    // More info https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
    public static boolean isSortable(int[] a) {
        return (countInversions(a) % 2 == 0);
    }

    private static int countInversions(int[] a) {
        int inversionsCount = 0;
        for (int out = 0; out < a.length; out++)
            for (int in = out + 1; in < a.length; in++)
                if (a[out] > a[in])
                    inversionsCount++;
        return inversionsCount;
    }

    public static boolean isSortableBruteForce(int[] a) {
        return false;
    }

}
