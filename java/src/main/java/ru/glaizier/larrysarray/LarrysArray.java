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

    // kind of bubble sort
    // check triples and reverse so max will be in the right position
    public static boolean isSortableBruteForce(int[] a) {
        // need to stop when there are no triples
        for (int out = 0; out < a.length - 2; out++) {
            boolean swapped = false;
            for (int in = 0; in < a.length - out - 2; in++) {
                if (!isMaxRight(a, in, in + 1, in + 2)) {
                    rotateMaxRight(a, in, in + 1, in + 2);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        return isSortedAsc(a);
    }

    private static boolean isSortedAsc(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1])
                return false;
        return true;
    }

    private static boolean isMaxRight(int[] a, int leftIndex, int middleIndex, int rightIndex) {
        return a[rightIndex] >= a[middleIndex] && a[rightIndex] >= a[leftIndex];
    }

    private static void rotateMaxRight(int[] a, int leftIndex, int middleIndex, int rightIndex) {
        while (!isMaxRight(a, leftIndex, middleIndex, rightIndex)) {
            int tmp = a[rightIndex];
            a[rightIndex] = a[middleIndex];
            a[middleIndex] = a[leftIndex];
            a[leftIndex] = tmp;
        }
    }

}
