package ru.glaizier.emassupercomputer;

import ru.glaizier.util.Pair;

/**
 * https://www.hackerrank.com/challenges/two-pluses
 */
public class EmasSupercomputer {

    public static int getCrossesSquareProduct(boolean[][] a) {

        Pair<Integer, Integer> firstCrossCenterCoordinate;
        Pair<Integer, Integer> secondCrossCenterCoordinate;

        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                getCrossSquare(row, col);
            }
        }
        return -1;
    }

    // -1 if it can't be cross
    private static int getCrossSquare(int row, int col) {
        return -1;
    }

}
