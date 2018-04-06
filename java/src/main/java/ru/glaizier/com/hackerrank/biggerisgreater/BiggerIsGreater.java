package ru.glaizier.com.hackerrank.biggerisgreater;

import ru.glaizier.util.Pair;

// https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
// Permutations
public class BiggerIsGreater {

    /**
     * https://www.hackerrank.com/challenges/bigger-is-greater
     * Given a word , rearrange the letters of  to construct another word  in such a way that  is lexicographically greater than . In case of multiple possible answers, find the lexicographically smallest one among them.
     * Time O(n)
     */
    public static String getSmallestGreater(String s) {
        assert s != null;

        Pair<Integer, Integer> suffix = getLongestSuffix(s);
        int suffixStart = suffix.getX();
        int suffixEnd = suffix.getY();

        int pivotIndex = suffixStart - 1;

        if (pivotIndex < 0)
            return s;

        StringBuilder sb = replaceMinBiggestInSuffixWithPivot(new StringBuilder(s), suffixStart, suffixEnd, pivotIndex);

        return swapSuffix(sb, suffixStart, suffixEnd);
    }

    private static Pair<Integer, Integer> getLongestSuffix(String s) {
        int suffixStart, suffixEnd;
        suffixStart = suffixEnd = s.length() - 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) >= s.charAt(i + 1))
                suffixStart = i;
            else
                return new Pair<>(suffixStart, suffixEnd);
        }
        return new Pair<>(suffixStart, suffixEnd);
    }

    private static StringBuilder replaceMinBiggestInSuffixWithPivot(StringBuilder sb, int suffixStart, int suffixEnd, int pivotIndex) {
        int i = suffixEnd;
        while (sb.charAt(i) <= sb.charAt(pivotIndex) && i >= suffixStart)
            i--;
        if (i < suffixStart)
            throw new IllegalStateException();

        swapChars(sb, pivotIndex, i);
        return sb;
    }

    private static void swapChars(StringBuilder sb, int firstIndex, int secondIndex) {
        char tmp = sb.charAt(firstIndex);
        sb.setCharAt(firstIndex, sb.charAt(secondIndex));
        sb.setCharAt(secondIndex, tmp);
    }

    private static String swapSuffix(StringBuilder sb, int suffixStart, int suffixEnd) {
        while (suffixEnd > suffixStart) {
            swapChars(sb, suffixStart, suffixEnd);
            suffixEnd--;
            suffixStart++;
        }
        return sb.toString();
    }

}
