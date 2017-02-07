package ru.glaizier.emassupercomputer;

import ru.glaizier.util.Pair;

/**
 * https://www.hackerrank.com/challenges/two-pluses
 */
public class EmasSuperComputer {

    public static int getCrossesSquareProduct(boolean[][] a) {

        Pair<Integer, Integer> firstCrossCenterCoordinate = null;
        int firstSquare = 0;
        Pair<Integer, Integer> secondCrossCenterCoordinate = null;
        int secondSquare = 0;

        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                int curSquare = getCrossSquare(a, row, col);
                if (curSquare > firstSquare) {
                    secondSquare = firstSquare;
                    secondCrossCenterCoordinate = firstCrossCenterCoordinate;
                    firstSquare = curSquare;
                    firstCrossCenterCoordinate = new Pair<>(row, col);
                    continue;
                }
                if (curSquare > secondSquare) {
                    secondSquare = curSquare;
                    secondCrossCenterCoordinate = new Pair<>(row, col);
                }
            }
        }
        return firstSquare * secondSquare;
    }

    // -1 if it can't be cross
    private static int getCrossSquare(boolean[][] a, int row, int col) {
        if (!a[row][col])
            return -1;

        // check four sides of current cells left, upper, right and down
        int[] good = {0, 0, 0, 0};
        int cur = col - 1;
        while (cur >= 0 && a[row][cur--])
            good[0]++;

        cur = row - 1;
        while (cur >= 0 && a[cur--][col])
            good[1]++;

        cur = col + 1;
        while (cur < a[row].length && a[row][cur++])
            good[2]++;

        cur = row + 1;
        while (cur < a.length && a[cur++][col])
            good[3]++;

        int min = Integer.MAX_VALUE;
        for (int el : good)
            if (el < min)
                min = el;
        return 1 + min * good.length;
    }

}
