package ru.glaizier.almostsorted;


import ru.glaizier.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/almost-sorted
 */

public class AlmostSorted {

    public static final String NO = "no";
    public static final String SWAP = "swap %s-%s";
    public static final String REVERSE = "reverse %s-%s";

    public static String canBeSorted(int[] a) {
        assert a != null;

        List<Pair<Integer, Integer>> desc = getDesc(a);

        if (desc.isEmpty())
            return "sorted";
        if (desc.size() > 2)
            return NO;

        if (desc.size() == 2) {
            int leftFirst = desc.get(0).getX();
            int rightFirst = desc.get(0).getY();
            int leftOther = desc.get(1).getX();
            int rightOther = desc.get(1).getY();

            if (Math.abs(rightFirst - leftFirst) > 1 || Math.abs(rightOther - leftOther) > 1)
                return NO;

            swap(a, leftFirst, rightOther);
            if (arePositionsSorted(a, leftFirst, rightOther))
                return String.format(SWAP, leftFirst, rightOther);
            else
                return NO;
        }

        // here we have only one desc
        int left = desc.get(0).getX();
        int right = desc.get(0).getY();

        if (right - left > 1) {
            reverse(a, left, right);
            if (arePositionsSorted(a, left, right))
                return String.format(REVERSE, left, right);
            else
                return NO;
        } else {
            // need to check two swaps
            swap(a, left, right);
            if (arePositionsSorted(a, left, right))
                return String.format(SWAP, left, right);
            if (right + 1 < a.length - 1) {
                swap(a, right, right + 1);
                if (arePositionsSorted(a, right, right + 1))
                    return String.format(SWAP, right, right + 1);
            }
        }

        return NO;
    }

    private static List<Pair<Integer, Integer>> getDesc(int[] a) {
        List<Pair<Integer, Integer>> desc = new ArrayList<>();
        int begin = -1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                if (begin == -1)
                    begin = i - 1;
            } else if (begin != -1) {
                desc.add(new Pair<>(begin, i - 1));
                begin = -1;
            }
        }
        if (begin != -1)
            desc.add(new Pair<>(begin, a.length - 1));
        return desc;
    }


    private static boolean arePositionsSorted(int[] a, int leftIndex, int rightIndex) {
        boolean leftSorted;
        boolean rightSorted;

        leftSorted = (leftIndex > 0) ? (a[leftIndex - 1] < a[leftIndex]) && (a[leftIndex] < a[leftIndex + 1]) :
                (a[leftIndex] < a[leftIndex + 1]);

        rightSorted = (rightIndex < a.length - 1) ? (a[rightIndex - 1] < a[rightIndex]) && (a[rightIndex] < a[rightIndex + 1]) :
                (a[rightIndex - 1] < a[rightIndex]);

        return leftSorted && rightSorted;
    }

    private static void swap(int[] a, int firstIndex, int otherIndex) {
        int tmp = a[firstIndex];
        a[firstIndex] = a[otherIndex];
        a[otherIndex] = tmp;
    }

    private static void reverse(int[] a, int leftIndex, int rightIndex) {
        while (leftIndex < rightIndex) {
            swap(a, leftIndex, rightIndex);
            leftIndex++;
            rightIndex--;
        }
    }

}
