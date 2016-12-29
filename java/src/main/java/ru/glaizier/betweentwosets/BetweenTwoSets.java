package ru.glaizier.betweentwosets;


import java.util.HashSet;
import java.util.Set;

/**
 * https://www.hackerrank.com/challenges/between-two-sets
 */

public class BetweenTwoSets {

    public static Set<Integer> getBetweenSet(Set<Integer> first, Set<Integer> second) {
        assert first != null;
        assert second != null;
        Integer firstMax = first.stream().max(Integer::compareTo).orElseThrow(IllegalStateException::new);
        Integer secondMin = second.stream().min(Integer::compareTo).orElseThrow(IllegalStateException::new);

        Set<Integer> betweenSet = new HashSet<>();

        for (int check = firstMax; check <= secondMin; check++) {
            boolean nextCheck = false;
            for (Integer f : first) {
                if (check % f != 0) {
                    nextCheck = true;
                    break;
                }
            }
            if (nextCheck)
                continue;
            for (Integer s : second) {
                if (s % check != 0) {
                    nextCheck = true;
                    break;
                }
            }
            if (!nextCheck)
                betweenSet.add(check);
        }

        return betweenSet;
    }


}
